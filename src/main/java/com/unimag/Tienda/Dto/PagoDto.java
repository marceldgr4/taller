package com.unimag.Tienda.Dto;

import com.unimag.Tienda.Entidad.Enum.EstadoPago;
import com.unimag.Tienda.Entidad.Enum.MetodoPago;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter

public class PagoDto {
    private Long id;
    private Long idPedido;
    private Double totalPago;
    private LocalDateTime fechaPago;
    private MetodoPago metodoPago;
    private String numeroTransaccion;
    private EstadoPago estadoPago;
}
