package com.unimag.Tienda.Integration;

import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Repository.ClienteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;



@DataJpaTest
@Testcontainers
public class ClienteIntegrationTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest");
    @BeforeEach
    public void setUp() {
        postgreSQLContainer.start();
    }

    @AfterEach
    public void tearDown() {
        postgreSQLContainer.stop();
    }

    @Test
    public void testGuardarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("nombre de cliente");
        cliente.setEmail("cliente@example.com");
        cliente.setDireccion("direccion de cliente");
        clienteRepository.save(cliente);
        List<Cliente> clientes = clienteRepository.findAll();
        Assertions.assertFalse(clientes.isEmpty());
        Assertions.assertEquals(cliente.getNombre(), clientes.get(0).getNombre());

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

        Optional<Cliente> clienteActualizado = clienteRepository.findById(clienteGuardado.getId());
        Assertions.assertTrue(clienteActualizado.isPresent());
        Assertions.assertEquals("nuevo nombre", clienteActualizado.get().getNombre());

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
        Assertions.assertFalse(clienteEliminado.isPresent());

    }
    @Test
    public void testBuscarClientePorId(){
        Cliente cliente =new Cliente();
        cliente.setNombre("nombre del cliente");
        cliente.setEmail("cliente@example.com");
        cliente.setDireccion("direccion del cliente");
        Cliente clienteGudardado= clienteRepository.save(cliente);
        Optional<Cliente>clienteEncontrado =clienteRepository.findById(clienteGudardado.getId());
        Assertions.assertTrue(clienteEncontrado.isPresent());
        Assertions.assertEquals(clienteGudardado.getId(), clienteEncontrado.get().getId());
    }


}
