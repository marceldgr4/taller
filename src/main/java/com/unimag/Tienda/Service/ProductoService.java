package com.unimag.Tienda.Service;

import com.unimag.Tienda.Dto.ProductoDto;
import com.unimag.Tienda.Entidad.Producto;
import com.unimag.Tienda.Mapper.ProductoMapper;
import com.unimag.Tienda.Repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductoService {
    private  final ProductoRepository productoRepository;
    @Autowired
        public ProductoService(ProductoRepository productoRepository){
        this.productoRepository =productoRepository;
    }
    public ProductoDto GuardarProducto(ProductoDto productoDto){
        Producto producto = ProductoMapper.INSTANCE.ProductoDtoToProducto(productoDto);
        Producto ProductoGuardado = productoRepository.save(producto);
        return ProductoMapper.INSTANCE.productoToProductoDto(ProductoGuardado);
    }
    public ProductoDto ActulizarProducto(Long id,ProductoDto productoDto){
        Producto ProductoActulizado =ProductoMapper.INSTANCE.ProductoDtoToProducto(productoDto);
        ProductoActulizado.setId(id);
        Producto ProductoGuardado= productoRepository.save(ProductoActulizado);
        /*
        Producto productoExistente = productoRepository.findById(id).orElseThrow(()-> new RuntimeException("producto no encontrado con ID:"+id));
        productoExistente.setNombreProducto(ProductoActulizado.getNombreProducto());
        productoExistente.setPrecio(ProductoActulizado.getPrecio());
        productoExistente.setStock(ProductoActulizado.getStock());*/

        return ProductoMapper.INSTANCE.productoToProductoDto(ProductoGuardado);
    }
    public static List<ProductoDto> BuscarProductoPorNombre(String term){
        List<Producto> productos = productoRepository.findByNombreContainingIgnoreCase(term);
        return productos.stream().map(ProductoMapper.INSTANCE::productoToProductoDto).collect(Collectors.toList());
    }
    public List<ProductoDto>BuscarProductoEnStock(){
        List<Producto> productos = productoRepository.findByStockGreaterThan(0);
        return productos.stream().map(ProductoMapper.INSTANCE::productoToProductoDto).collect(Collectors.toList());

    }
    public List<ProductoDto>BuscarProductoPorPrecioYStock(Double PrecioMaximo,Integer StockMaximo){
       List<Producto> productos = productoRepository.findByPriceLessThanAndStockLessThanEqual(PrecioMaximo, StockMaximo);
        return productos.stream().map(ProductoMapper.INSTANCE::productoToProductoDto).collect(Collectors.toList());
    }
    public  void EliminnarProducto(Long id){
        Producto producto = productoRepository.findById(id).orElseThrow(()-> new RuntimeException("producto no encontrado con ID:"+id));
        productoRepository.delete(producto);
    }

    public List<ProductoDto> obtenerProductosEnStock() {
        List<Producto> productosEnStock = productoRepository.findByStockGreaterThan(0);
        return productosEnStock.stream()
                .map(ProductoMapper.INSTANCE::productoToProductoDto)
                .collect(Collectors.toList());
    }

    public ProductoDto obtenerProductoPorId(Long id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isEmpty()){
            Producto producto = productoOptional.get();
            return ProductoMapper.INSTANCE.productoToProductoDto(producto);
        }else {
            throw new NoSuchElementException("no se encotro ningun problema con el ID"+id);
        }

    }
}
