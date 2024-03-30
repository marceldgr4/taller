package com.unimag.Tienda.Integration;

import com.unimag.Tienda.Entidad.Enum.EstadoPedido;
import com.unimag.Tienda.Entidad.ItemPedido;
import com.unimag.Tienda.Entidad.Pedido;
import com.unimag.Tienda.Entidad.Producto;
import com.unimag.Tienda.Repository.ItemPedidoRepository;
import com.unimag.Tienda.Repository.PedidoRepository;
import com.unimag.Tienda.Repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@DataJpaTest
@Testcontainers
public class ItemPedidoIntegrationTest {
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("tiendamicro")
            .withUsername("postgres")
            .withPassword("1234");

    @Test
    public void testGuardarItemPedido() {

        Pedido pedido = new Pedido();
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setStatus(EstadoPedido.valueOf("PENDIENTE"));
        pedidoRepository.save(pedido);

        Producto product = new Producto();
        product.setNombreProducto("Producto de Prueba");
        product.setPrecio(10.0);
        product.setStock(100);
        productoRepository.save(product);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPedido(pedido);
        itemPedido.setProducto(product);
        itemPedido.setCantidad(5);
        itemPedido.setPrecioUnitario(10.0);


        ItemPedido itemPedidoGuardado = itemPedidoRepository.save(itemPedido);


        assertNotNull(itemPedidoGuardado.getId());
        assertEquals(pedido.getId(), itemPedidoGuardado.getPedido().getId());
        assertEquals(product.getId(), itemPedidoGuardado.getProducto().getId());
        assertEquals(Optional.of(5), itemPedidoGuardado.getCantidad());
        assertEquals(Optional.of(10.0), itemPedidoGuardado.getPrecioUnitario());
    }
}
