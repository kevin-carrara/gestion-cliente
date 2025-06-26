package com.technicaltestpinapp.service.impl;

import com.technicaltestpinapp.dto.ClienteDto;
import com.technicaltestpinapp.dto.ClienteResponseKpi;
import com.technicaltestpinapp.dto.ClienteResponseMuerte;
import com.technicaltestpinapp.exception.ServiceException;
import com.technicaltestpinapp.model.Cliente;
import com.technicaltestpinapp.repository.ClienteRepository;
import com.technicaltestpinapp.service.ClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.technicaltestpinapp.mapper.ClienteMapper.CLIENTE_MAPPER;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClientService {

    @Autowired
    private final ClienteRepository clienteRepository;

    private final int ESPERANZA_DE_VIDA = 75;
    private final int PLUS_ESPERANZA = 5;
    private final String GENERO = "femenino";


    @Transactional
    @Override
    public ClienteDto createCliente(ClienteDto clienteRequest) {
        if (isNull(clienteRequest.getFechaNacimiento())|| isNull(clienteRequest.getPeso()) ||isNull(clienteRequest.getAltura())){
            throw new ServiceException("No puede haber campos null, revise!!", HttpStatus.BAD_REQUEST);
        }
        Cliente clienteEntity = CLIENTE_MAPPER.toEntity(clienteRequest);
        clienteEntity.setEdad(calcularEdad(clienteRequest.getFechaNacimiento()));
        return CLIENTE_MAPPER.toDto(clienteRepository.save(clienteEntity));
    }

    @Override
    public ClienteResponseKpi getClienteKpi() {

        List<Cliente> clienteList = clienteRepository.findAll();
        if (clienteList.isEmpty()) {
            return new ClienteResponseKpi(0.0,0.0);
        }
        double promedioEdadesCliente = calcularPromedioEdades(clienteList);
        double desviacionEstandarClientes = calcularDesviacionEstandar(clienteList,promedioEdadesCliente);

        return new ClienteResponseKpi(promedioEdadesCliente,desviacionEstandarClientes);

    }

    @Override
    public List<ClienteResponseMuerte> getClientList() {
        List<Cliente> clienteList = clienteRepository.findAll();
        return clienteList.stream()
                .map(client -> {
                    ClienteResponseMuerte dtoResponse = CLIENTE_MAPPER.clienteToClienteResonseMuerte(client);
                    dtoResponse.setFechaProbableMuerte(calcularFechaProbableMuerte(client));
                    return dtoResponse;
                })
                .collect(Collectors.toList());
    }

    private double calcularIMC(Cliente cliente){
        return cliente.getPeso() / (cliente.getAltura() * cliente.getAltura());
    }

    private LocalDate calcularFechaProbableMuerte(Cliente cliente){

        double imc = calcularIMC(cliente);
        int ajusteEsperanzaVida = 0;

        if (cliente.getGenero().equalsIgnoreCase(GENERO)){
            ajusteEsperanzaVida += 5;
        }
        if (cliente.isFumador()){
            ajusteEsperanzaVida -= 10;
        }
        if (imc > 30){
            ajusteEsperanzaVida -= 5;
        }

        LocalDate fechaProbableMuerte = cliente.getFechaNacimiento().plusYears(ESPERANZA_DE_VIDA + ajusteEsperanzaVida);
        if (fechaProbableMuerte.isBefore(LocalDate.now())) {
            fechaProbableMuerte = LocalDate.now().plusYears(PLUS_ESPERANZA);
        }

        return fechaProbableMuerte;
    }


    private double calcularPromedioEdades(List<Cliente> clienteList){
        return clienteList.stream()
                .mapToInt(Cliente::getEdad)
                .average()
                .orElse(0);
    }

    private double calcularDesviacionEstandar(List<Cliente> clienteList, double promedio){
        double suma = clienteList.stream()
                .mapToDouble(c -> Math.pow(c.getEdad() - promedio, 2))
                .sum();
        return Math.sqrt(suma / clienteList.size());
    }

    private int calcularEdad(LocalDate fechaNacimiento) {
        return LocalDate.now().getYear() - fechaNacimiento.getYear() -
                (LocalDate.now().getDayOfYear() < fechaNacimiento.getDayOfYear() ? 1 : 0);
    }
}
