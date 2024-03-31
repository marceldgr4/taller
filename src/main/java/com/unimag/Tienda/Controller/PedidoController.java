package com.unimag.Tienda.Controller;

import com.unimag.Tienda.Entidad.Pedido;
import com.unimag.Tienda.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido pedidoGuardado = pedidoService.GuardarPedido(pedido);
        return new ResponseEntity<>(pedidoGuardado, HttpStatus.CREATED);
    }

    @PutMapping("/{pedidoId}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable Long pedidoId, @RequestBody Pedido pedido) {
        pedido.setId(pedidoId);
        Pedido pedidoActualizado = pedidoService.actualizarPedido(pedido);
        return ResponseEntity.ok(pedidoActualizado);
    }

    @DeleteMapping("/{pedidoId}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long pedidoId) {
        pedidoService.EliminarPedido(pedidoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<Optional<Pedido>> ObtenerPedidosPorCliente(@PathVariable Long clienteId) {
        Optional<Pedido> pedidos = pedidoService.BuscarPedidosPorCliente(clienteId);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Pedido>> BuscarPedidosPorFecha(@RequestParam(name = "startDate") Date startDate,
                                                              @RequestParam(name = "endDate") Date endDate) {
        List<Pedido> pedidos = pedidoService.BuscarPedidoEntreFecha(startDate, endDate);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<Pedido> ObtenerPedidoPorId(@PathVariable Long idpedido) {
        Pedido pedido = pedidoService.buscarPedidoPorId(idpedido);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
