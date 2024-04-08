package com.Unimagda.STienda.Mapper.Mappers;


import com.Unimagda.STienda.DTO.Save.ItemPedidoDtoSave;
import com.Unimagda.STienda.DTO.Send.ItemPedidoDtoSend;
import com.Unimagda.STienda.Entity.ItemPedido;
import com.Unimagda.STienda.Mapper.MapperGeneral;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ItemPedidoMapper extends MapperGeneral<ItemPedidoDtoSend, ItemPedidoDtoSave, ItemPedido> {



}
