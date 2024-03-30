package com.unimag.Tienda.Service;

import com.unimag.Tienda.Entidad.Enum.MetodoPago;
import com.unimag.Tienda.Entidad.Pago;

import java.time.LocalDate;
import java.util.List;

public interface PagoService {
    static Pago crearPago(Pago pago);
    List<Pago>obtenerTodoLosPagos();
    List<Pago>obtenerPagosPorRangoFecha(LocalDate startDate, LocalDate endDate);
    List<Pago>obtenerPagoPorOrdenYMetodoPago(Long idPedido, MetodoPago metodoPago);
}
