package com.unimag.Tienda.Controller;

import com.unimag.Tienda.Entidad.Enum.MetodoPago;
import com.unimag.Tienda.Entidad.Pago;
import com.unimag.Tienda.Service.PagoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;

    @Autowired
    public PagoController(PagoService pagoService){
        this.pagoService = pagoService;
    }

    @PostMapping
    public ResponseEntity<Pago> CrearPago(@RequestBody Pago pago){
        Pago NuevoPago = pagoService.CrearPago(pago);
        return new ResponseEntity<>(NuevoPago,HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Pago>> obtenerTodoLosPagos(){
        List<Pago> pagos= pagoService.obtenerTodoLosPagos();
       return  new ResponseEntity<>(pagos, HttpStatus.OK);
    }
    @GetMapping("/por-rango-fecha")
    public ResponseEntity<List<Pago>> obtenerPagosPorRangoFecha(
            @RequestParam("fechaInicio") LocalDateTime startDate,
            @RequestParam("fechaFin") LocalDateTime endDate) {
        List<Pago> pagos = pagoService.ObtenerPagosPorRangoFecha(startDate,endDate);
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }
    @GetMapping("/por-orden-y-metodo-pago")
    public ResponseEntity<List<Pago>> obtenerPagosPorOrdenYMetodoPago(
            @RequestParam("idPedido") Long idPedido,
            @RequestParam("metodoPago") MetodoPago metodoPago) {
        List<Pago> pagos = pagoService.ObtenerPagoPorOrdenYMetodoPago(idPedido, metodoPago);
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }
}
