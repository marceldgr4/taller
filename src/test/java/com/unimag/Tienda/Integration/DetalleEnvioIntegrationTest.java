package com.unimag.Tienda.Integration;

import com.unimag.Tienda.Entidad.DetalleEnvio;
import com.unimag.Tienda.Entidad.Enum.EstadoPedido;
import com.unimag.Tienda.Entidad.Pedido;
import com.unimag.Tienda.Repository.DetalleEnvioRepository;
import com.unimag.Tienda.Repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.Optional;


import static org.junit.Assert.*;

@DataJpaTest
@Testcontainers
public class DetalleEnvioIntegrationTest {
@Autowired
    private DetalleEnvioRepository detalleEnvioRepository;
@Autowired
    private PedidoRepository pedidoRepository;
@Container
public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
        .withDatabaseName("tiendamicro")
        .withUsername("postgres")
        .withPassword("1234");
@Test
    public void testGuardarDetalleEnvio(){
    Pedido pedido = new Pedido();
    pedido.setFechaPedido(LocalDateTime.now());
    pedido.setStatus(EstadoPedido.PENDIENTE);
    pedidoRepository.save(pedido);

    DetalleEnvio detalleEnvio =new DetalleEnvio();
    detalleEnvio.setPedido(pedido);
    detalleEnvio.setDireccion("Dirección de Envío");
    detalleEnvio.setTransportadora("Servicio de Envío Express");
    detalleEnvio.setNumeroGuia("123456789");

    DetalleEnvio detalleEnvioGuardado = detalleEnvioRepository.save(detalleEnvio);
    assertNotNull(detalleEnvioGuardado.getId());
    assertEquals(pedido.getId(),detalleEnvioGuardado.getPedido().getId());
    assertEquals("Dirección de Envío", detalleEnvioGuardado.getDireccion());
    assertEquals("Servicio de Envío Express", detalleEnvioGuardado.getTransportadora());
    assertEquals("123456789", detalleEnvioGuardado.getNumeroGuia());
    }
    @Test
    public void testBuscarDetalleEnvioPorId(){
    Pedido pedido = new Pedido();
    pedido.setFechaPedido(LocalDateTime.now());
    pedido.setStatus(EstadoPedido.PENDIENTE);
    pedidoRepository.save(pedido);

    DetalleEnvio detalleEnvio = new DetalleEnvio();
    detalleEnvio.setPedido(pedido);
    detalleEnvio.setDireccion("direccion de envio");
    detalleEnvio.setTransportadora("servicio de envio");
    detalleEnvio.setNumeroGuia("123412");
    detalleEnvioRepository.save(detalleEnvio);
    Optional<DetalleEnvio>detalleEnvioEncotrado = detalleEnvioRepository.findByidPedido(pedido.getId());
    assertTrue(detalleEnvioEncotrado.isPresent());
    assertEquals(detalleEnvio.getId(),detalleEnvioEncotrado.get().getId());

    }
    @Test
    public void testEliminarDetalleEnvio(){
    Pedido pedido = new Pedido();
    pedido.setFechaPedido(LocalDateTime.now());
    pedido.setStatus(EstadoPedido.PENDIENTE);
    pedidoRepository.save(pedido);

    DetalleEnvio detalleEnvio =new DetalleEnvio();
    detalleEnvio.setPedido(pedido);
    detalleEnvio.setDireccion("direccion de envio");
    detalleEnvio.setTransportadora("servicio de transportadora");
    detalleEnvio.setNumeroGuia("numero de guia");
    DetalleEnvio detalleEnvioGuardado= detalleEnvioRepository.save(detalleEnvio);
    detalleEnvioRepository.delete(detalleEnvioGuardado);
    assertFalse(detalleEnvioRepository.existsById(detalleEnvioGuardado.getId()));

    }
    public void testActualizarDetalleEnvio() {
        Pedido pedido = new Pedido();
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setStatus(EstadoPedido.PENDIENTE);
        pedidoRepository.save(pedido);

        DetalleEnvio detalleEnvio = new DetalleEnvio();
        detalleEnvio.setPedido(pedido);
        detalleEnvio.setDireccion("Dirección de Envío");
        detalleEnvio.setTransportadora("Servicio de Envío Express");
        detalleEnvio.setNumeroGuia("123456789");
        detalleEnvioRepository.save(detalleEnvio);


        detalleEnvio.setDireccion("Nueva Dirección");
        detalleEnvio.setTransportadora("Nuevo Servicio de Envío");
        detalleEnvio.setNumeroGuia("987654321");


        DetalleEnvio detalleEnvioActualizado = detalleEnvioRepository.save(detalleEnvio);


    }


}
