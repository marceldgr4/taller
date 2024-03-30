package com.unimag.Tienda.Integration;

import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.Assert.*;

@DataJpaTest
@Testcontainers
public class ClienteIntegrationTest {
    @Autowired
    private ClienteRepository clienteRepository;
    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("tiendamicro")
            .withUsername("postgres")
            .withPassword("1234");

    @Test
    public void testGuardarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("nombre de cliente");
        cliente.setEmail("cliente@example.com");
        cliente.setDireccion("direccion de cliente");

        Cliente clienteGuardado = clienteRepository.save(cliente);
        assertNotNull(clienteGuardado.getId());
        assertEquals("Nombre del Cliente", clienteGuardado.getNombre());
        assertEquals("cliente@example.com", clienteGuardado.getEmail());
        assertEquals("Dirección del Cliente", clienteGuardado.getDireccion());
    }
    @Test
    public void TestActualizarCliente(){
        Cliente cliente = new Cliente();
        cliente.setNombre("nombre de cliente");
        cliente.setEmail("cliente@example.com");
        cliente.setDireccion("direccion del cliente ");
        Cliente clienteGuardado = clienteRepository.save(cliente);

        clienteGuardado.setNombre("nuevo nombre");
        clienteRepository.save(clienteGuardado);

        Optional<Cliente> clienteActulizado = clienteRepository.findById(clienteGuardado.getId());
        assertTrue(clienteActulizado.isPresent());
        assertEquals("nuevo nombre",clienteActulizado.get().getNombre());

    }
    @Test
    public void  testEliminarCliente(){
        Cliente cliente =new Cliente();
        cliente.setNombre("nombre del cliente");
        cliente.setEmail("cliente@example.com");
        cliente.setDireccion("direccion del cliente");
        Cliente clienteGuardado = clienteRepository.save(cliente);

        clienteRepository.delete(clienteGuardado);
        Optional<Cliente>clienteEliminado = clienteRepository.findById(clienteGuardado.getId());
        assertFalse(clienteEliminado.isPresent());

    }
    @Test
    public void testBuscarClientePorId(){
        Cliente cliente =new Cliente();
        cliente.setNombre("nombre del cliente");
        cliente.setEmail("cliente@example.com");
        cliente.setDireccion("direccion del cliente");
        Cliente clienteGudardado= clienteRepository.save(cliente);
        Optional<Cliente>clienteEncontrado =clienteRepository.findById(clienteGudardado.getId());
        assertTrue(clienteEncontrado.isPresent());
        assertEquals(clienteGudardado.getId(),clienteEncontrado.get().getId());
    }


}
