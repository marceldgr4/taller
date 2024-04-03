package com.unimag.Tienda.Mapper;

import com.unimag.Tienda.Dto.ProductoDto;
import com.unimag.Tienda.Entidad.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper (componentModel = "spring")
public interface ProductoMapper {

    ProductoDto productoToProductoDto(Producto producto);
    Producto ProductoDtoToProducto(ProductoDto productoDto);
}
