package com.unimag.Tienda.Service;

import com.unimag.Tienda.Entidad.Producto;
import com.unimag.Tienda.Repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductoService {
    private  final ProductoRepository productoRepository;
    @Autowired
        public ProductoService(ProductoRepository productoRepository){
        this.productoRepository =productoRepository;
    }
    public Producto GuardarProducto(Producto producto){
        return productoRepository.save(producto);
    }
    public Producto ActulizarProducto(Long id,Producto productoActulizado){
        Producto productoExistente = productoRepository.findById(id).orElseThrow(()-> new RuntimeException("producto no encontrado con ID:"+id));
        productoExistente.setNombreProducto(productoActulizado.getNombreProducto());
        productoExistente.setPrecio(productoActulizado.getPrecio());
        productoExistente.setStock(productoActulizado.getStock());
        return productoRepository.save(productoExistente);
    }
    public List<Producto> BuscarProductoPorNombre(String term){
        return productoRepository.findByNombreContainingIgnoreCase(term);
    }
    public List<Producto>BuscarProductoEnStock(){
        return productoRepository.findByStockGreaterThan(0);
    }
    public List<Producto>BuscarProductoPorPrecioYStock(Double PrecioMaximo,Integer StockMaximo){
        return productoRepository.findByPriceLessThanAndStockLessThanEqual(PrecioMaximo, StockMaximo);
    }
    public  void EliminnarProducto(Long id){
        Producto producto = productoRepository.findById(id).orElseThrow(()-> new RuntimeException("producto no encontrado con ID:"+id));
        productoRepository.delete(producto);
    }
}
