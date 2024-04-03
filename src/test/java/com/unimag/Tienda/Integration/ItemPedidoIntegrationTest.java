package com.unimag.Tienda.Integration;

import com.unimag.Tienda.Entidad.Enum.EstadoPedido;
import com.unimag.Tienda.Entidad.ItemPedido;
import com.unimag.Tienda.Entidad.Pedido;
import com.unimag.Tienda.Entidad.Producto;
import com.unimag.Tienda.Repository.ItemPedidoRepository;
import com.unimag.Tienda.Repository.PedidoRepository;
import com.unimag.Tienda.Repository.ProductoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import java.time.LocalDateTime;
import java.util.List;


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
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest");

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


        Assertions.assertNotNull(itemPedidoGuardado.getId());
        Assertions.assertEquals(pedido.getId(), itemPedidoGuardado.getPedido().getId());
        Assertions.assertEquals(product.getId(), itemPedidoGuardado.getProducto().getId());
        Assertions.assertEquals((5), itemPedidoGuardado.getCantidad());
        Assertions.assertEquals((10.0), itemPedidoGuardado.getPrecioUnitario());
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
        Assertions.assertTrue(itemPedidoRepository.findById(itemPedido.getId()).isEmpty());

    }
    @Test
    public void testActulizarItemPedido(){
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
     Assertions.assertNotNull(UpdateItemPedido);
     Assertions.assertEquals((10), UpdateItemPedido.getCantidad());
     Assertions.assertEquals((20.0), UpdateItemPedido.getPrecioUnitario());

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
