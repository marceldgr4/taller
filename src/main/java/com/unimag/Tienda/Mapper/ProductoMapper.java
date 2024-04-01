package com.unimag.Tienda.Mapper;

import com.unimag.Tienda.Dto.ProductoDto;
import com.unimag.Tienda.Entidad.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductoMapper {
ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);
    @Mapping(target = "nombreProducto",source = "nombreProducto")
    @Mapping(target = "precio",source = "precio")
    @Mapping(target = "Stock",source = "Stock")

    ProductoDto productoToProductoDto(Producto producto);

    @Mapping(target = "nombreProducto",source = "nombreProducto")
    @Mapping(target = "precio",source = "precio")
    @Mapping(target = "Stock",source = "Stock")
    Producto ProductoDtoToProducto(ProductoDto productoDto);
}
