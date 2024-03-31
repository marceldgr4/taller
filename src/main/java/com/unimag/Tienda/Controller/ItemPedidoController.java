package com.unimag.Tienda.Controller;

import com.unimag.Tienda.Dto.ItemPedidoDto;
import com.unimag.Tienda.Entidad.ItemPedido;
import com.unimag.Tienda.Service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemPedido")
public class ItemPedidoController {
    private final ItemPedidoService itemPedidoService;

    @Autowired
    public ItemPedidoController(ItemPedidoService itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<ItemPedido>> buscarPorPedidoId(@PathVariable Long pedidoId) {
        List<ItemPedido> items = itemPedidoService.buscarPorPedidoId(pedidoId);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<ItemPedido>> buscarPorProductoId(@PathVariable Long productoId) {
        List<ItemPedido> items = itemPedidoService.buscarPorProductoId(productoId);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/producto/{productoId}/total-ventas")
    public ResponseEntity<Double> CalcularTotalVentasPorProducto(@PathVariable Long productoId) {
        Double totalVentas = itemPedidoService.CalcularTotalVentasProducto(productoId);
        return ResponseEntity.ok(totalVentas);
    }
    @PostMapping("/crear")
    public ResponseEntity<ItemPedido> crearItemPedido(@RequestBody ItemPedidoDto itemPedidoDTO) {
        ItemPedido newItemPedido = itemPedidoService.crearItemPedido(itemPedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newItemPedido);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ItemPedido> actualizarItemPedido(@PathVariable Long id, @RequestBody ItemPedidoDto itemPedidoDTO) {
        ItemPedido updatedItemPedido = itemPedidoService.ActualizarItemPedido(id, itemPedidoDTO);
        return ResponseEntity.ok(updatedItemPedido);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarItemPedido(@PathVariable Long id) {
        itemPedidoService.EliminarItemPedido(id);
        return ResponseEntity.noContent().build();
    }
}
