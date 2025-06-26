package com.technicaltestpinapp.service;

import com.technicaltestpinapp.dto.ClienteDto;
import com.technicaltestpinapp.dto.ClienteResponseKpi;
import com.technicaltestpinapp.dto.ClienteResponseMuerte;

import java.util.List;

public interface ClientService {

    ClienteDto createCliente(ClienteDto clienteRequest);

    ClienteResponseKpi getClienteKpi();

    List<ClienteResponseMuerte> getClientList();
}
