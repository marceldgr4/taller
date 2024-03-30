package com.unimag.Tienda.Service;

import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicio {
@Autowired
    private ClienteRepository clienteRepository;
public List<Cliente>EncotrarPorEmail(String email){
    return  clienteRepository.findByEmail(email);
}
public List<Cliente>EncontrarPorDireccion(String Direccion){
    return clienteRepository.findByDireccion(Direccion);
    }
    public List<Cliente> EncontrarPorNombreQueEmpieceCon(String Nombre) {
        return clienteRepository.findByNombreStartingWithIgnoreCase(Nombre);
    }
}
