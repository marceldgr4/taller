package com.unimag.Tienda.Mapper;

import com.unimag.Tienda.Dto.PedidoDto;
import com.unimag.Tienda.Entidad.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PedidoMapeer {
    PedidoMapeer INSTANCE = Mappers.getMapper(PedidoMapeer.class);
    @Mapping(target = "id",ignore = true)
    Pedido pedidoDtoToPedido(PedidoDto pedido);
    PedidoDto pedidoToPedidoDto(Pedido pedido);
}
