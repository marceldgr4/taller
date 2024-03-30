package com.unimag.Tienda.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleEnvioDto {
    private Long id;
    private Long idPedido;
    private String direccion;
    private  String transportadora;
    private String numeroGuia;
}
