package com.unimag.Tienda.Service.Interface;

import com.unimag.Tienda.Dto.ClienteDto;

import java.util.List;

public interface ClienteServiceInterface {
   ClienteDto CreateCliente(ClienteDto clienteDto);


   ClienteDto ObtenerClientePorId(Long id);
   ClienteDto ObtenerClientePorNombre(String nombre);

   ClienteDto ActualizarCliente(ClienteDto clienteDto);

   ClienteDto ObtenerClientePorEmail(String email);

   void eliminarCliente(Long id);

   List<ClienteDto> ObtenerTodoLosClientesPrNombre(String nombre);
   List<ClienteDto> ObtenerClientePorCity(String city);
   List<ClienteDto> ObtenerClientePorDireccion(String direccion);
}
