package com.unimag.Tienda.Mapper;

import com.unimag.Tienda.Dto.ClienteDto;
import com.unimag.Tienda.Entidad.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClienteMapper {
ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
@Mapping(target = "id", ignore = true)
    ClienteDto clienteToClienteDto(Cliente cliente);
    Cliente clienteDtoToCliente(ClienteDto clienteDto);
   // List<ClienteDto> clientesToClientesDto(List<Cliente>clientes);


    List<ClienteDto> clientesToClientesDto(List<ClienteDto> clienteDtos);

    List<ClienteDto> clienteToClientesDto(List<Cliente> clientes);
}
