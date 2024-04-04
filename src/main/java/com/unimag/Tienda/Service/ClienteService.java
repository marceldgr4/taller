package com.unimag.Tienda.Service;

import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
//-----------CRUD-------------
    public Cliente CrearCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente ObtenerClientePorID(Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isEmpty()){
            return cliente.get();
        }else {
            throw new RuntimeException("cliente no encontrado con ID"+id);

        }
    }
    public Cliente ActulizarCliente(Long id, Cliente cliente){
        cliente.setId(id);
        return clienteRepository.save(cliente);

    }
    public void EliminarCliente(Long id){
        clienteRepository.deleteById(id);
    }
//-----------------------------------------
//Encontrar clientes por email
    public List<Cliente> BuscarPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }
//Encontrar clientes por dirección
    public List<Cliente> BuscarPorDireccion(String Direccion) {
        return clienteRepository.findByDireccion(Direccion);
    }
//Encontrar clientes por todos los clientes que comiencen por un nombre
    public List<Cliente> BuscarPorNombreQueEmpieceCon(String Nombre) {
        return clienteRepository.findByNombreStartingWithIgnoreCase(Nombre);
    }

    public List<Cliente> ObternerClientePorCity(String cityName) {
        return clienteRepository.findByCiudad(cityName);
    }
//-----------_DTO-------------------

}
