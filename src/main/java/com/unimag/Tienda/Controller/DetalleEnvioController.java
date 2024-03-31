package com.unimag.Tienda.Controller;

import com.unimag.Tienda.Entidad.DetalleEnvio;
import com.unimag.Tienda.Service.DetalleEnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detalleEnvio")
public class DetalleEnvioController {
    @Autowired
    private DetalleEnvioService detalleEnvioService;
    @PostMapping("/Guardar")
    public ResponseEntity<DetalleEnvio> GuardarDetalleEncio(@RequestBody DetalleEnvio detalleEnvio) {
        DetalleEnvio savedDetalleEnvio =detalleEnvioService.GuardarDetalleEnvio(detalleEnvio);
        return new ResponseEntity<>(savedDetalleEnvio, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DetalleEnvio> obtenerDetalleEnvioPorId(@PathVariable Long id) {
        Optional<DetalleEnvio> detalleEnvio = detalleEnvioService.ObtenerDetalleEnvioPorId(id);
        return detalleEnvio.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<DetalleEnvio>> obtenerTodosLosDetallesDeEnvio() {
        List<DetalleEnvio> detalleEnvios = detalleEnvioService.ObtenerTodosLosDetallesDeEnvio();
        return new ResponseEntity<>(detalleEnvios, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalleEnvio(@PathVariable Long id) {
        detalleEnvioService.EliminarDetalleEnvio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

