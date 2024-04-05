package com.unimag.Tienda.Unit.RepositoryTest;

import com.unimag.Tienda.Dto.ProductoDto;
import com.unimag.Tienda.Entidad.Producto;
import com.unimag.Tienda.Repository.ProductoRepository;
import com.unimag.Tienda.Service.ProductoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class ProductoRepositoryTest {
@Mock
    private ProductoRepository productoRepository;
@InjectMocks
private ProductoService productoService;

}
