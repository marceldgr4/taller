package com.unimag.Tienda.Controller;

import com.unimag.Tienda.Dto.ProductoDto;
import com.unimag.Tienda.Service.ProductoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @PostMapping
    public ResponseEntity<ProductoDto> save(@RequestBody ProductoDto productoDto){
        productoDto productoSaved= productoService.save(productoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoSaved);
    }
}
