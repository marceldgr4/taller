package com.unimag.Tienda.Integration;

import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
}
