package com.unimag.Tienda.Controller;

import com.unimag.Tienda.Dto.ProductoDto;
import com.unimag.Tienda.Entidad.Producto;
import com.unimag.Tienda.Mapper.ProductoMapper;
import com.unimag.Tienda.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductoController {
    public final ProductoService productoService;
    @Autowired
    public ProductoController(ProductoService productoService){
        this.productoService =productoService;
    }
    @PostMapping
    public ProductoDto GuardarProducto(@RequestBody Producto producto){
        ProductoDto productoDto = ProductoMapper.INSTANCE.productoToProductoDto(producto);
        return productoService.CrearProducto(new ProductoDto());
    }
    // Obtener un producto por su ID (GET)
    @GetMapping("/{id}")
    public ProductoDto obtenerProductoPorId(@PathVariable Long id) {
        return productoService.ObtenerProductoPorId(id);
    }

    // Actualizar un producto existente (PUT)
    @PutMapping("/api/v1/products/{id}")
    public ProductoDto ActulizarProducto(@PathVariable Long id,@RequestBody ProductoDto productoDto){
        return productoService.ActulizarProducto(id, productoDto);
    }

    // Eliminar un producto por su ID (DELETE)
    @DeleteMapping("/api/v1/products/{id}")
    public  void EliminarProducto(@PathVariable Long id){
        productoService.EliminarProducto(id);
    }
    // Búsqueda de productos (GET)
    @GetMapping("/api/v1/products/search")
    public List<ProductoDto>BuscarProductoPorNombre(@RequestParam(required = false)String term){
        if(term !=null){
            return productoService.BuscarProductoPorNombre(term);
        }else {
            return productoService.BuscarProductoEnStock();
        }
    }
    @GetMapping("/api/v1/producto/search")
    public List<ProductoDto> BuscarProductoPorPrecioYStock(@RequestParam Double PrecioMaximo, @RequestParam Integer StockMaximo){
        return productoService.BuscarProductoPorPrecioYStock(PrecioMaximo,StockMaximo);
    }
    // Obtener productos en stock (GET)
    @GetMapping("/api/v1/products/instock")
    public List<ProductoDto> obtenerProductosEnStock() {
        return productoService.obtenerProductosEnStock();
    }
    // Crear un nuevo producto (POST)
    @PostMapping
    public ProductoDto CrearNuevoProducto(@RequestBody ProductoDto productoDto) {
        return productoService.CrearProducto(productoDto);
    }
}
