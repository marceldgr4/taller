package com.unimag.Tienda.Mapper;

import com.unimag.Tienda.Dto.ItemPedidoDto;
import com.unimag.Tienda.Entidad.ItemPedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {
ItemPedidoDto itemPedidoToItemPedidoDto(ItemPedido itemPedido);
ItemPedido itemPedidoDtoToItemPedido(ItemPedidoDto itemPedidoDto);
}
