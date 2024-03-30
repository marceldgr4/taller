package com.unimag.Tienda.Controller;

import com.unimag.Tienda.Entidad.Pago;
import com.unimag.Tienda.Service.PagoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pagos")
public class PagoController {
    private final PagoService pagoService;

    @PostMapping
    public Pago crearPago(@RequestBody Pago pago){
        return PagoService.crearPago(pago);

    }
    @GetMapping
    public List<Pago> obtenerTodoLosPagos(){
        return pagoService.obtenerTodoLosPagos();
    }
}
