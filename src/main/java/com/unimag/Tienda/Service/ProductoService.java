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

    //--------------crudo---------------------

    public  void EliminarProducto(Long id){
        Producto producto = productoRepository.findById(id).orElseThrow(()-> new RuntimeException("producto no encontrado con ID:"+id));
        productoRepository.delete(producto);
    }
    //-------------------------------------------------
    //---buscar productos según un término de búsqueda---

    }

    //----Buscar los productos que están en stock.----


    //----------Buscar los productos que no superen un precio y un stock determinado-----







