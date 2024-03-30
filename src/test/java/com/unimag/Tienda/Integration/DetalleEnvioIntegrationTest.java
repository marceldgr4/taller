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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
}
