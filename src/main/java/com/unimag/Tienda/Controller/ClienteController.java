package com.unimag.Tienda.Controller;

import com.unimag.Tienda.Dto.ClienteDto;
import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ClienteController {
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;

    }

    @GetMapping("/{id")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Cliente clienteDto = clienteService.ObtenerClientePorID(id);
        if (clienteDto != null) {
            return ResponseEntity.ok(clienteDto);
        } else {
            return ResponseEntity.notFound().build();
        }


    }
    @GetMapping("/City")
    public ResponseEntity<List<Cliente>> ObtenerClientesPorCiudad(@RequestParam("CityName") String CityName){
        List<Cliente> clienteDtos = clienteService.ObternerClientePorCity(CityName);
        return ResponseEntity.ok(clienteDtos);

    }
}
