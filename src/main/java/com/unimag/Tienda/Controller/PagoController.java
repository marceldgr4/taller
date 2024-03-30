package com.unimag.Tienda.Controller;

import com.unimag.Tienda.Entidad.Pago;
import com.unimag.Tienda.Service.PagoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Pago> crearPago(@RequestBody Pago pago){
        Pago nuevoPago = PagoService.crearPago(pago);
        return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Pago>> obtenerTodoLosPagos(){
        List<Pago> pagos= pagoService.obtenerTodoLosPagos();
       return  new ResponseEntity<>(pagos, HttpStatus.OK);
    }
}
