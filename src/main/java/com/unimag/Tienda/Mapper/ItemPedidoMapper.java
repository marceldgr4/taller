package com.unimag.Tienda.Mapper;

import com.unimag.Tienda.Dto.ItemPedidoDto;
import com.unimag.Tienda.Entidad.ItemPedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemPedidoMapper {
ItemPedidoMapper INSTANCE = Mappers.getMapper(ItemPedidoMapper.class);
@Mapping(target = "id",ignore = true)
    ItemPedido itemPedidoDtoToItemPedido(ItemPedidoDto itemPedidoDto);
ItemPedidoDto itemPedidoToItemPedidoDto(ItemPedido itemPedido);
}
