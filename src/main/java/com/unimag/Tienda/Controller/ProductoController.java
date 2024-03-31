package com.unimag.Tienda.Controller;

import com.unimag.Tienda.Entidad.Producto;
import com.unimag.Tienda.Service.PedidoService;
import com.unimag.Tienda.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    public final ProductoService productoService;
    @Autowired
    public ProductoController(ProductoService productoService){
        this.productoService =productoService;
    }
    @PostMapping
    public Producto GuardarProducto(@RequestBody Producto producto){
        return productoService.GuardarProducto(producto);
    }
    @PutMapping("/{id}")
    public Producto ActulizarProducto(@PathVariable Long id,@RequestBody Producto producto){
        return productoService.ActulizarProducto(id, producto);
    }
    @DeleteMapping("/{id}")
    public  void EliminarProducto(@PathVariable Long id){
        productoService.EliminnarProducto(id);
    }
    @GetMapping
    public List<Producto>BuscarProductoPorNombre(@RequestParam(required = false)String term){
        if(term !=null){
            return productoService.BuscarProductoPorNombre(term);
        }else {
            return productoService.BuscarProductoEnStock();
        }
    }
    @GetMapping("/Buscar")
    public List<Producto> BuscarProductoPorPrecioYStock(@RequestParam Double PrecioMaximo, @RequestParam Integer StockMaximo){
        return productoService.BuscarProductoPorPrecioYStock(PrecioMaximo,StockMaximo);
    }
}
