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
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

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
@DynamicPropertySource
static void setPostgreSQLContainer(DynamicPropertyRegistry registry){
    registry.add("spring.datasoirce.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

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
    @Test
    public void testEliminarItemPedido() {
        Pedido pedido = new Pedido();
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setStatus(EstadoPedido.PENDIENTE);
        pedidoRepository.save(pedido);


        Producto producto = new Producto();
        producto.setNombreProducto("nombre del producto");
        producto.setPrecio(10.0);
        producto.setStock(100);
        productoRepository.save(producto);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPedido(pedido);
        itemPedido.setProducto(producto);
        itemPedido.setCantidad(5);
        itemPedido.setPrecioUnitario(10.0);
        itemPedidoRepository.save(itemPedido);
        itemPedidoRepository.deleteById(itemPedido.getId());
        assertTrue(itemPedidoRepository.findById(itemPedido.getId()).isEmpty());

    }
    @Test
    public void testAcutlizarItemPedido(){
    Pedido pedido =new Pedido();
    pedido.setFechaPedido(LocalDateTime.now());
    pedido.setStatus(EstadoPedido.PENDIENTE);
    pedidoRepository.save(pedido);

    Producto producto =new Producto();
    producto.setNombreProducto("nombre de producto");
    producto.setPrecio(10.0);
    producto.setStock(100);
    productoRepository.save(producto);

    ItemPedido itemPedido = new ItemPedido();
    itemPedido.setPedido(pedido);
    itemPedido.setProducto(producto);
    itemPedido.setCantidad(5);
    itemPedido.setPrecioUnitario(10.0);
    itemPedidoRepository.save(itemPedido);
     itemPedido.setCantidad(10);
     itemPedido.setPrecioUnitario(20.0);
     itemPedidoRepository.save(itemPedido);

     ItemPedido UpdateItemPedido =itemPedidoRepository.findById(itemPedido.getId()).orElse(null);
     assertNotNull(UpdateItemPedido);
     assertEquals(Optional.of(10), UpdateItemPedido.getCantidad());
     assertEquals(Optional.of(20.0), UpdateItemPedido.getPrecioUnitario());

    }
    @Test
    public void testBuscarItemPedidoPorId(){
    Pedido pedido = new Pedido();
    pedido.setFechaPedido(LocalDateTime.now());
    pedido.setStatus(EstadoPedido.PENDIENTE);
    pedidoRepository.save(pedido);

    Producto producto =new Producto();
    producto.setNombreProducto("nombre del producto");
    producto.setPrecio(10.0);
    producto.setStock(100);
    productoRepository.save(producto);

    List<ItemPedido> itemPedidoEncontrados=itemPedidoRepository.findByidPedido(pedido.getId());



    }

}
