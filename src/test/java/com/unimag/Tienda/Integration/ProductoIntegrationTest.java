package com.unimag.Tienda.Integration;

import com.unimag.Tienda.Entidad.Producto;

import com.unimag.Tienda.Repository.ProductoRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@DataJpaTest
@Testcontainers

public class ProductoIntegrationTest {
@Autowired
    private ProductoRepository productoRepository;
@Container
    public static PostgreSQLContainer<?> postgreSQLContainer =new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("tiendamicro")
            .withUsername("postgres")
            .withPassword("1234");
@Test
    public void testGuardarProducto(){
    Producto producto = new Producto();
    producto.setNombreProducto("producto de prueba");
    producto.setPrecio(10.0);
    producto.setStock(100);
    Producto productoGuardado = productoRepository.save(producto);

    assertNotNull(productoGuardado.getId());
    assertEquals("Producto de prueba", productoGuardado.getNombreProducto());
    assertEquals(Optional.of(10.0), productoGuardado.getPrecio());
    assertEquals(Optional.of(100), productoGuardado.getStock());
}
}
