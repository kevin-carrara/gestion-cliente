package com.technicaltestpinapp.controller;


import com.technicaltestpinapp.dto.ClienteDto;
import com.technicaltestpinapp.dto.ClienteResponseKpi;
import com.technicaltestpinapp.dto.ClienteResponseMuerte;
import com.technicaltestpinapp.service.ClientService;
import com.technicaltestpinapp.service.kafka.KafkaProducerService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cliente")
@RequiredArgsConstructor
public class ClienteController {

    @Autowired
    private final ClientService clienteService;

    private final KafkaProducerService kafkaProducerService;


    @PostMapping("/creacliente")
    @Operation(summary = "crear clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Full authentication is required to access this resource"),
    })
    public ResponseEntity<ClienteDto> crearCliente(@Valid @RequestBody ClienteDto clienteDto){


        ClienteDto response = clienteService.createCliente(clienteDto);

        String message = String.format("Cliente creado: %s %s", response.getNombre(), response.getApellido());
        kafkaProducerService.sendMessage(message);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/kpideclientes")
    @Operation(summary = "ver promedio de edad y desviacion estandar de todos los clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Full authentication is required to access this resource"),
    })
    public ResponseEntity<ClienteResponseKpi> getClienteKpi(){

        return ResponseEntity.ok(clienteService.getClienteKpi());

    }

    @GetMapping("/listClientes")
    @Operation(summary = "listado de todos los clientes con su fecha probable de muerte")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Full authentication is required to access this resource"),
    })
    public ResponseEntity<List<ClienteResponseMuerte>> getClientList(){

        return ResponseEntity.ok(clienteService.getClientList());

    }


}
