package com.unimag.Tienda.Integration;

import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Entidad.Enum.EstadoPedido;
import com.unimag.Tienda.Entidad.Pedido;
import com.unimag.Tienda.Repository.ClienteRepository;
import com.unimag.Tienda.Repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@DataJpaTest
@Testcontainers
public class PedidoIntegrationTest {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Container
    public static PostgreSQLContainer<?>postgreSQLContainer =new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("tiendamicro")
            .withUsername("postgres")
            .withPassword("1234");
    @Test
    public void testGuardarPedido(){
        Cliente cliente =new Cliente();
        cliente.setNombre("Nombre del Cliente");
        cliente.setEmail("cliente@example.com");
        cliente.setDireccion("Dirección del Cliente");
        clienteRepository.save(cliente);

        Pedido pedido =new Pedido();
        pedido.setCliente(cliente);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setStatus(EstadoPedido.valueOf("PENDIENTE"));

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        assertNotNull(pedidoGuardado.getId());
        assertEquals(cliente.getId(),pedidoGuardado.getCliente().getId());
        assertEquals("PENDIENTE",pedidoGuardado.getStatus());

    }
}
