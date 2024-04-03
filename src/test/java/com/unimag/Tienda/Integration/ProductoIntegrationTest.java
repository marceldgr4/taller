package com.unimag.Tienda.Integration;

import com.unimag.Tienda.Entidad.Producto;

import com.unimag.Tienda.Repository.ProductoRepository;

import org.junit.jupiter.api.Assertions;
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

public class ProductoIntegrationTest {
@Autowired
    private ProductoRepository productoRepository;
@Container
    public static PostgreSQLContainer<?> postgreSQLContainer =new PostgreSQLContainer<>("postgres:latest");
@Test
    public void tesCrearProducto(){
    Producto producto = new Producto();
    producto.setNombreProducto("producto de prueba");
    producto.setPrecio(10.0);
    producto.setStock(100);
    Producto productoGuardado = productoRepository.save(producto);

    Assertions.assertNotNull(productoGuardado.getId());
    Assertions.assertEquals("Producto de prueba", productoGuardado.getNombreProducto());
    Assertions.assertEquals((10.0), productoGuardado.getPrecio());
    Assertions.assertEquals((100), productoGuardado.getStock());
}
@Test
public void testLeerProductoPorID(){
    Producto producto = new Producto();
    producto.setNombreProducto("producto de prueba");
    producto.setPrecio(10.0);
    producto.setStock(100);
    Producto productoGuardado = productoRepository.save(producto);
    Optional<Producto>productoLeido =productoRepository.findById(productoGuardado.getId());

    Assertions.assertTrue(productoLeido.isPresent());
    Assertions.assertEquals(productoGuardado, productoLeido.get());
}
    @Test
    public void testActualizarProducto() {
        Producto producto = new Producto();
        producto.setNombreProducto("producto de prueba");
        producto.setPrecio(10.0);
        producto.setStock(100);
        Producto productoGuardado = productoRepository.save(producto);

        productoGuardado.setPrecio(15.0);
        productoGuardado.setStock(150);
        productoRepository.save(productoGuardado);

        Producto productoActualizado = productoRepository.findById(productoGuardado.getId()).orElse(null);
        Assertions.assertNotNull(productoActualizado);
        Assertions.assertEquals((15.0), productoActualizado.getPrecio());
        Assertions.assertEquals((150), productoActualizado.getStock());
    }

    @Test
    public void testEliminarProducto() {
        Producto producto = new Producto();
        producto.setNombreProducto("producto de prueba");
        producto.setPrecio(10.0);
        producto.setStock(100);
        Producto productoGuardado = productoRepository.save(producto);

        productoRepository.delete(productoGuardado);

        Producto productoEliminado = productoRepository.findById(productoGuardado.getId()).orElse(null);
        Assertions.assertNull(productoEliminado);
    }

    @Test
    public void testBuscarProductoPorNombre() {
        Producto producto = new Producto();
        producto.setNombreProducto("producto de prueba");
        producto.setPrecio(10.0);
        producto.setStock(100);
        productoRepository.save(producto);

        List<Producto> productosEncontrados = productoRepository.findByNombreContainingIgnoreCase("producto");
        Assertions.assertEquals(1, productosEncontrados.size());
        Assertions.assertEquals("producto de prueba", productosEncontrados.get(0).getNombreProducto());
    }
}
