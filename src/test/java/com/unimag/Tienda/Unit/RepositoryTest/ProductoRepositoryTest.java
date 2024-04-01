package com.unimag.Tienda.Unit.RepositoryTest;

import com.unimag.Tienda.Dto.ProductoDto;
import com.unimag.Tienda.Entidad.Producto;
import com.unimag.Tienda.Repository.ProductoRepository;
import com.unimag.Tienda.Service.ProductoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@DataJpaTest
public class ProductoRepositoryTest {
@Mock
    private ProductoRepository productoRepository;
@InjectMocks
private ProductoService productoService;
@Test
    public void testBuscarPorNombre(){
    List<Producto> productos =new ArrayList<>();
    when(productoRepository.findByNombreContainingIgnoreCase("term")).thenReturn(productos);
    List<ProductoDto> ProductosEncotrados = productoService.BuscarProductoPorNombre("term");
}
}
