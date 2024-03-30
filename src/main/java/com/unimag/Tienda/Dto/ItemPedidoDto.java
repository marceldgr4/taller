package com.unimag.Tienda.Dto;
import lombok.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ItemPedidoDto {
    private Long id;
    private  Long idProducto;
    private Integer cantidad;
    private Double precioUnitario;
    private  Double total;

}
