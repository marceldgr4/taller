package com.unimag.Tienda.Service;

import com.unimag.Tienda.Repository.ClienteRepository;
import com.unimag.Tienda.Dto.ClienteDto;
import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Mapper.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    private ClienteMapper clienteMapper;

    //-----------CRUD-------------
    public Cliente CrearCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }
    public Cliente ActulizarCliente(Long id, Cliente cliente){
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    public void EliminarCliente(Long id){
        clienteRepository.deleteById(id);
    }


//-------------------FIN DEL CRUD----------------

//-----------------------------------------------
//Encontrar clientes por email
    public List<Cliente> BuscarPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }
//Encontrar clientes por dirección
    public List<Cliente> BuscarPorDireccion(String Direccion) {
        return clienteRepository.findByDireccion(Direccion);
    }
//Encontrar clientes por todos los clientes que comiencen por un nombre
    public List<Cliente> BuscarPorNombreQueEmpiecePor(String Nombre) {
        return clienteRepository.findByNombreStartingWithIgnoreCase(Nombre);
    }

    public List<Cliente> ObtenerClientePorCity(String cityName) {
        return clienteRepository.findByCiudad(cityName);
    }
    //----------------------------------------------------------
    //---------------------------------DTO-----------------------
public ClienteDto CrearCliente(ClienteDto clienteDto){
        Cliente cliente = clienteMapper.clienteDtoToCliente(clienteDto);
       Cliente clienteGuardado =clienteRepository.save(cliente);
       return clienteMapper.clienteToClienteDto(clienteGuardado);
}

public ClienteDto ActualizarClienteDto(Long id,ClienteDto clienteDto){
        Cliente cliente = clienteMapper.clienteDtoToCliente(clienteDto);
        cliente.setId(id);
        Cliente clienteActualizado =clienteRepository.save(cliente);
        return clienteMapper.clienteToClienteDto(clienteActualizado);
}

    public ClienteDto ObtenerClientePorID(Long id){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            return clienteMapper.clienteToClienteDto(cliente);
        }else {
            return null;
        }
    }
    public List<ClienteDto> ObtenerTodoLosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(clienteMapper::clienteToClienteDto).collect(Collectors.toList());
    }
    public ClienteDto GuardarCliente(ClienteDto clienteDto){
        Cliente cliente = clienteMapper.clienteDtoToCliente(clienteDto);
        Cliente clienteGuardado =clienteRepository.save(cliente);
        return clienteMapper.clienteToClienteDto(clienteGuardado);

    }



}
