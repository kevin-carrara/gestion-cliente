package com.technicaltestpinapp.mapper;

import com.technicaltestpinapp.dto.ClienteDto;
import com.technicaltestpinapp.dto.ClienteResponseMuerte;
import com.technicaltestpinapp.model.Cliente;
import com.technicaltestpinapp.service.impl.ClienteServiceImpl;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {

    ClienteMapper CLIENTE_MAPPER = Mappers.getMapper(ClienteMapper.class);

    ClienteDto toDto(Cliente clienteEntity);
    Cliente toEntity(ClienteDto clienteDto);

    ClienteResponseMuerte clienteToClienteResonseMuerte(Cliente cliente);


}
