package com.unimag.Tienda.Mapper;

import com.unimag.Tienda.Dto.ClienteDto;
import com.unimag.Tienda.Entidad.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDto clienteToClienteDto(Cliente cliente);
    Cliente clienteDtoToCliente(ClienteDto clienteDto);


    List<ClienteDto> clientesToClientesDto(List<ClienteDto> clienteDtos);

    List<ClienteDto> clienteToClientesDto(List<Cliente> clientes);
}
