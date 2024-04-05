package com.unimag.Tienda.api.v1.payments;

import com.unimag.Tienda.Dto.PagoDto;
import com.unimag.Tienda.Entidad.Enum.MetodoPago;
import com.unimag.Tienda.Entidad.Pago;
import com.unimag.Tienda.Mapper.PagoMapper;
import com.unimag.Tienda.Service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/v1/payments")
public class PagoController {

    private final PagoService pagoService;
    private final PagoMapper pagoMapper;


    @Autowired
    public PagoController(PagoService pagoService, PagoMapper pagoMapper){
        this.pagoService = pagoService;
        this.pagoMapper=pagoMapper;
    }
@GetMapping("/api/v1/payments/{id}")
public PagoDto ObtenerPagoPorId(@PathVariable Long id){
        Pago pago = pagoService.ObtenerPagoPorId(id);
        return pagoMapper.pagoToPagoDto(pago);
}


    @PostMapping("api/v1/payments")
    public ResponseEntity<PagoDto> CrearPago(@RequestBody PagoDto pagoDto){
       PagoDto nuevoPagoDto =pagoService.CrearPago(pagoDto);
       return  ResponseEntity.status(HttpStatus.CREATED).body(nuevoPagoDto);
    }
    @GetMapping("/api/v1/payments")
    public ResponseEntity<List<PagoDto>> obtenerTodoLosPagos(){
        List<Pago> pagos= pagoService.obtenerTodoLosPagos();
        List<PagoDto> pagoDtos =pagoMapper.pagosToPagoDtos(pagos);
        return  ResponseEntity.ok(pagoDtos);
    }
    @GetMapping("/api/v1/payments/date-range?startDate= & endDate=")
    public ResponseEntity<List<PagoDto>> obtenerPagosPorRangoFecha(
            @RequestParam("fechaInicio") LocalDateTime startDate,
            @RequestParam("fechaFin") LocalDateTime endDate) {
        List<Pago> pagos = pagoService.ObtenerPagosPorRangoFecha(startDate,endDate);
        List<PagoDto>pagoDtos =pagoMapper.pagosToPagoDtos(pagos);
        return  ResponseEntity.ok(pagoDtos);
    }
    @GetMapping("/api/v1/payments/order/{orderId}")
    public ResponseEntity<List<PagoDto>> ObtenerPagosPorOrdenYMetodoPago(
            @RequestParam("idPedido") Long idPedido,
            @RequestParam("metodoPago") MetodoPago metodoPago) {
        List<Pago> pagos = pagoService.ObtenerPagoPorOrdenYMetodoPago(idPedido, metodoPago);
        List<PagoDto>pagoDtos =pagoMapper.pagosToPagoDtos(pagos);
        return ResponseEntity.ok(pagoDtos);
    }
    @PutMapping("/api/v1/payments/{id}")
    public ResponseEntity<PagoDto> ActulizarPago(@PathVariable Long id,@RequestBody PagoDto pagoDto){
        Pago pago =pagoMapper.pagoDtoToPago(pagoDto);
        Pago pagoActulizado =pagoService.actualizarPago(id,pago);
        PagoDto pagoActulizadoDto =pagoMapper.pagoToPagoDto(pagoActulizado);
        return ResponseEntity.ok(pagoActulizadoDto);
    }
    @DeleteMapping("/api/v1/payments/{id}")
    public ResponseEntity<Void>eliminarPago(@PathVariable Long id){
        pagoService.EliminarPago(id);
        return ResponseEntity.noContent().build();
    }
}
