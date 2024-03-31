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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
        List<Pago> pagoList =pagoRepository.findAll();

        assertNotNull(pagoGuardado.getId());
        assertEquals(pedido.getId(),pagoGuardado.getPedido().getId());
        assertEquals(Optional.of(50.0),pagoGuardado.getTotalPago());
        assertEquals(MetodoPago.PSE,pagoGuardado.getMetodoPago());
    }
    @Test
    public void testActlizarPago(){
        Pago pago =new Pago();
        pago.setTotalPago(50.0);
        pago.setFechaPago(LocalDateTime.now());
        pago.setMetodoPago(MetodoPago.PSE);

        pago = pagoRepository.save(pago);
        pago.setTotalPago(60.0);
        pagoRepository.save(pago);

        Pago pagoActulizado= pagoRepository.findById(pago.getId()).orElse(null);
        assertEquals(Optional.of(60.0),pagoActulizado.getTotalPago());
    }
    @Test
    public void testEliminarPago(){
        Pago pago =new Pago();
        pago.setTotalPago(50.0);
        pago.setFechaPago(LocalDateTime.now());
        pago.setMetodoPago(MetodoPago.PSE);
        pago =pagoRepository.save(pago);

        pagoRepository.delete(pago);
        assertTrue(pagoRepository.findById(pago.getId()).isEmpty());
    }
    @Test
    public void testBuscarPago(){

        List<Pago> pagosEncontrado =pagoRepository.findAll();
        assertTrue(pagosEncontrado.isEmpty());

    }
}
