package com.unimag.Tienda.Controller;

import com.unimag.Tienda.Dto.PedidoDto;
import com.unimag.Tienda.Entidad.Pedido;
import com.unimag.Tienda.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
   @GetMapping("/api/v1/orders/{id}")
    public ResponseEntity<PedidoDto> ObtenerPedidoPorID(@PathVariable Long id){
       PedidoDto pedidoDto = pedidoService.ObtenerPedidoPorId(id);
       return ResponseEntity.ok(pedidoDto);
   }
    @GetMapping("/api/v1/orders")
        public ResponseEntity<List<PedidoDto>>ObtenerTodoLosPedidos(){
        List<PedidoDto>pedidoDtos =pedidoService.ObtenerTodoLosPedidos();
        return ResponseEntity.ok(pedidoDtos);
}

@GetMapping("/api/v1/orders/customer/{customerId}")
public ResponseEntity<List<PedidoDto>>buscarPedidosPorCliente(@PathVariable Long customerId){
       List<PedidoDto> pedidoDtos = pedidoService.buscarPedidosPorCliente(customerId);
       return ResponseEntity.ok(pedidoDtos);

}

    @PostMapping("/api/v1/orders")
    public ResponseEntity<PedidoDto> CrearPedido(@RequestBody PedidoDto pedidoDto) {
        PedidoDto CrearPedidoDto = pedidoService.CrearPedido(pedidoDto);
        return new ResponseEntity<>(CrearPedidoDto, HttpStatus.CREATED);
    }

    @PutMapping("/api/v1/orders/{id}")
    public ResponseEntity<PedidoDto> ActualizarPedido(@PathVariable Long id, @RequestBody PedidoDto pedidoDto) {
        PedidoDto pedidoActualizadoDto = pedidoService.ActualizarPedido(id,pedidoDto);
        return ResponseEntity.ok(pedidoActualizadoDto);
    }

    @DeleteMapping("/api/v1/orders/{id}")
    public ResponseEntity<Void> EliminarPedido(@PathVariable Long id) {
        pedidoService.EliminarPedido(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<Optional<Pedido>> ObtenerPedidosPorCliente(@PathVariable Long clienteId) {
        Optional<Pedido> pedidos = pedidoService.BuscarPedidosPorCliente(clienteId);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("//api/v1/orders/date-range?startDate= & endDate=")
    public ResponseEntity<List<Pedido>> BuscarPedidosPorFecha(@RequestParam(name = "startDate") LocalDateTime startDate,
                                                              @RequestParam(name = "endDate") LocalDateTime endDate) {
        List<Pedido> pedidosDto = pedidoService.BuscarPedidoEntreFecha(startDate, endDate);
        return ResponseEntity.ok(pedidosDto);
    }

}
