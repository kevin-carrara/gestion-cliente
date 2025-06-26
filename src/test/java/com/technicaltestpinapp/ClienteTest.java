package com.technicaltestpinapp;

import com.technicaltestpinapp.controller.ClienteController;
import com.technicaltestpinapp.dto.ClienteDto;
import com.technicaltestpinapp.dto.ClienteResponseKpi;
import com.technicaltestpinapp.dto.ClienteResponseMuerte;
import com.technicaltestpinapp.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteTest {

    @Mock
    private ClienteServiceImpl clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private ClienteDto clienteDto;
    private ClienteResponseKpi kpi;
    private List<ClienteResponseMuerte> mockResponse;


    @BeforeEach
    void setUp() {
        clienteDto = new ClienteDto("Juan","perez",LocalDate.of(1998,10,10) ,"masculino",1.70,75.0,false);
        kpi = new ClienteResponseKpi(35.5, 5.0);
        mockResponse = Arrays.asList(
                new ClienteResponseMuerte("Juan", "Pérez", LocalDate.of(1980, 5, 20), "M", 1.75, 70, true, 40, LocalDate.of(2070, 5, 20)),
                new ClienteResponseMuerte("Ana", "Gomez", LocalDate.of(1985, 8, 15), "F", 1.65, 60, false, 36, LocalDate.of(2081, 8, 15))
        );

    }
    @Test
    void testCrearCliente() {
        when(clienteService.createCliente(any(ClienteDto.class))).thenReturn(clienteDto);

        ResponseEntity<ClienteDto> response = clienteController.crearCliente(clienteDto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Juan", response.getBody().getNombre());
        assertEquals("perez",response.getBody().getApellido());
        assertEquals(LocalDate.of(1998,10,10),response.getBody().getFechaNacimiento());
        assertEquals("masculino",response.getBody().getGenero());
        assertFalse(response.getBody().isFumador());
        assertEquals(1.70,response.getBody().getAltura());
        assertEquals(75.0,response.getBody().getPeso());
    }

    @Test
    void testGetClienteKpi() {
        when(clienteService.getClienteKpi()).thenReturn(kpi);

        ResponseEntity<ClienteResponseKpi> response = clienteController.getClienteKpi();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetClientList() {

        when(clienteService.getClientList()).thenReturn(mockResponse);

        ResponseEntity<List<ClienteResponseMuerte>> response = clienteController.getClientList();

        ClienteResponseMuerte clienteTest = Objects.requireNonNull(response.getBody()).get(0);

        assertEquals("Juan", clienteTest.getNombre());
        assertEquals("Pérez", clienteTest.getApellido());
        assertEquals(LocalDate.of(1980, 5, 20), clienteTest.getFechaNacimiento());
        assertEquals("M", clienteTest.getGenero());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty(), "La lista de clientes no debe estar vacía");
        assertEquals(2, response.getBody().size(), "Debe retornar exactamente dos clientes");
        // Verificamos que se haya llamado al servicio
        verify(clienteService).getClientList();
    }



}
