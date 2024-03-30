package com.unimag.Tienda.Dto;
import  lombok.*;

import com.unimag.Tienda.Entidad.Enum.EstadoPago;
import com.unimag.Tienda.Entidad.Enum.MetodoPago;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter

public class pagoDto {
    private Long id;
    private Long idPedido;
    private Double totalPago;
    private LocalDateTime fechaPago;
    private MetodoPago metodoPago;
    private String numeroTransaccion;
    private EstadoPago estadoPago;
}
