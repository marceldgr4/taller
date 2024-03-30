package com.unimag.Tienda.Dto;


import com.unimag.Tienda.Entidad.ItemPedido;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter

public class PedidoDto {
    private Long id;
    private  Long idCliente;
    private LocalDateTime fechaPedido;
    private  String status;
    private List<ItemPedidoDto> itemPedidoDtos;
}
