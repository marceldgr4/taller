package com.unimag.Tienda.Controller;

import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")

public class ClienteController {
@Autowired
    private ClienteService clienteService;
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente clienteCreado = clienteService.CrearCliente(cliente);
        return new ResponseEntity<>(clienteCreado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.ObtenerClientePorID(id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> ActualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteActualizado = clienteService.ActulizarCliente(id, cliente);
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.EliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/email")
    public ResponseEntity<List<Cliente>> BuscarClientesPorEmail(@RequestParam String email) {
        List<Cliente> clientesEncontrados = clienteService.BuscarPorEmail(email);
        return ResponseEntity.ok(clientesEncontrados);
    }

    @GetMapping("/buscar/direccion")
    public ResponseEntity<List<Cliente>> BuscarClientesPorDireccion(@RequestParam String direccion) {
        List<Cliente> clientesEncontrados = clienteService.BuscarPorDireccion(direccion);
        return ResponseEntity.ok(clientesEncontrados);
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<Cliente>> BuscarClientesPorNombreQueEmpieceCon(@RequestParam String nombre) {
        List<Cliente> clientesEncontrados = clienteService.BuscarPorNombreQueEmpieceCon(nombre);
        return ResponseEntity.ok(clientesEncontrados);
    }
}
