package com.unimag.Tienda.ServiceTest;

import com.unimag.Tienda.Dto.ProductoDto;
import com.unimag.Tienda.Entidad.Producto;
import com.unimag.Tienda.Mapper.ProductoMapper;
import com.unimag.Tienda.Repository.ProductoRepository;
import com.unimag.Tienda.Service.ProductoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;




import static org.mockito.Mockito.when;

public class ProductoServiceTest {
    @Mock
    private ProductoRepository productoRepository;
    @InjectMocks
    private ProductoService productoService;
    @BeforeEach
    public void ActulizarProducto(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCrearProducto(){
        ProductoDto productoDto= new ProductoDto();
        productoDto.setNombreProducto("nombre de prueba");
        productoDto.setPrecio(10.0);
        productoDto.setStock(99);
        Producto productoCreado= ProductoMapper.INSTANCE.ProductoDtoToProducto(productoDto);
        when(productoRepository.save(productoCreado)).thenReturn(productoCreado);

        ProductoDto productoCreadoDto = productoService.CrearProducto(productoDto);

        Assertions.assertEquals("Producto de prueba", productoCreadoDto.getNombreProducto());
        Assertions.assertEquals((10.0), productoCreadoDto.getPrecio());
        Assertions.assertEquals((99), productoCreadoDto.getStock());
    }
}
