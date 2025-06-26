package com.technicaltestpinapp.mapper;

import com.technicaltestpinapp.dto.ClienteDto;
import com.technicaltestpinapp.dto.ClienteResponseMuerte;
import com.technicaltestpinapp.model.Cliente;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-26T10:51:29-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.14 (OpenLogic)"
)
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteDto toDto(Cliente clienteEntity) {
        if ( clienteEntity == null ) {
            return null;
        }

        String nombre = null;
        String apellido = null;
        LocalDate fechaNacimiento = null;
        String genero = null;
        Double altura = null;
        Double peso = null;
        boolean fumador = false;

        nombre = clienteEntity.getNombre();
        apellido = clienteEntity.getApellido();
        fechaNacimiento = clienteEntity.getFechaNacimiento();
        genero = clienteEntity.getGenero();
        altura = clienteEntity.getAltura();
        peso = clienteEntity.getPeso();
        fumador = clienteEntity.isFumador();

        ClienteDto clienteDto = new ClienteDto( nombre, apellido, fechaNacimiento, genero, altura, peso, fumador );

        return clienteDto;
    }

    @Override
    public Cliente toEntity(ClienteDto clienteDto) {
        if ( clienteDto == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setNombre( clienteDto.getNombre() );
        cliente.setApellido( clienteDto.getApellido() );
        cliente.setFechaNacimiento( clienteDto.getFechaNacimiento() );
        cliente.setGenero( clienteDto.getGenero() );
        cliente.setFumador( clienteDto.isFumador() );
        if ( clienteDto.getPeso() != null ) {
            cliente.setPeso( clienteDto.getPeso() );
        }
        if ( clienteDto.getAltura() != null ) {
            cliente.setAltura( clienteDto.getAltura() );
        }

        return cliente;
    }

    @Override
    public ClienteResponseMuerte clienteToClienteResonseMuerte(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        String nombre = null;
        String apellido = null;
        LocalDate fechaNacimiento = null;
        String genero = null;
        double altura = 0.0d;
        double peso = 0.0d;
        boolean fumador = false;
        Integer edad = null;

        nombre = cliente.getNombre();
        apellido = cliente.getApellido();
        fechaNacimiento = cliente.getFechaNacimiento();
        genero = cliente.getGenero();
        altura = cliente.getAltura();
        peso = cliente.getPeso();
        fumador = cliente.isFumador();
        edad = cliente.getEdad();

        LocalDate fechaProbableMuerte = null;

        ClienteResponseMuerte clienteResponseMuerte = new ClienteResponseMuerte( nombre, apellido, fechaNacimiento, genero, altura, peso, fumador, edad, fechaProbableMuerte );

        return clienteResponseMuerte;
    }
}
