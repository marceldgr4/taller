package com.unimag.Tienda.ServiceTest;

import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;
    @BeforeEach
    void SetUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void BuscarPorEmail(){
        String email ="clientes@example.com";
        Cliente cliente1= new Cliente();
        Cliente cliente2 = new Cliente();
        List<Cliente> clientes = Arrays.asList(cliente1,cliente2);

        when(clienteRepository.findByEmail(email)).thenReturn(clientes);
        List<Cliente>ClientesEncontrados = clienteRepository.findByEmail(email);
    }
}
