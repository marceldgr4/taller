package com.unimag.Tienda.Service;

import com.unimag.Tienda.Entidad.Enum.MetodoPago;
import com.unimag.Tienda.Entidad.Pago;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class PagoService {
    public static Pago crearPago(Pago pago) {
        return null;
    }

    public List<Pago> obtenerTodoLosPagos() {
        return null;
    }

    public  List<Pago> obtenerPagosPorRangoFecha(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    List<Pago> obtenerPagoPorOrdenYMetodoPago(Long idPedido, MetodoPago metodoPago) {
        return null;
    }
}
