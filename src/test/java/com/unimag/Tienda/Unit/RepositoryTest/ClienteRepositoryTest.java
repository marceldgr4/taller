package com.unimag.Tienda.Unit.RepositoryTest;

import com.unimag.Tienda.Dto.ClienteDto;
import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Repository.ClienteRepository;
import com.unimag.Tienda.Service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteRepositoryTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    public  void testBuscarPorEmail(){
        String email="cliente@example.com";
        Cliente cliente1 = new Cliente();
        Cliente cliente2 =new Cliente();
        List<Cliente>clientes = Arrays.asList(cliente1,cliente2);
        when(clienteRepository.findByEmail(email)).thenReturn(clientes);
       // List<ClienteDto> ClienteEncotrados = clienteService.BuscarPorEmail(email);

    }
}
