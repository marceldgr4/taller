package com.unimag.Tienda.Integration;

import com.unimag.Tienda.Entidad.Enum.EstadoPedido;
import com.unimag.Tienda.Entidad.Enum.MetodoPago;
import com.unimag.Tienda.Entidad.Pago;
import com.unimag.Tienda.Entidad.Pedido;
import com.unimag.Tienda.Repository.PagoRepository;
import com.unimag.Tienda.Repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@DataJpaTest
@Testcontainers
public class PagoIntegrationTest {
    @Autowired
    private PagoRepository pagoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("tiendamicro")
            .withUsername("postgres")
            .withPassword("1234");

    @Test
    public void testGuardarPago(){
        Pedido pedido = new Pedido();
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setStatus(EstadoPedido.PENDIENTE);
        pedidoRepository.save(pedido);

        Pago pago =new Pago();
        pago.setPedido(pedido);
        pago.setTotalPago(50.0);
        pago.setFechaPago(LocalDateTime.now());
        pago.setMetodoPago(MetodoPago.PSE);

        Pago pagoGuardado = pagoRepository.save(pago);
        assertNotNull(pagoGuardado.getId());
        assertEquals(pedido.getId(),pagoGuardado.getPedido().getId());
        assertEquals(Optional.of(50.0),pagoGuardado.getTotalPago());
        assertEquals(MetodoPago.PSE,pagoGuardado.getMetodoPago());
    }
}
