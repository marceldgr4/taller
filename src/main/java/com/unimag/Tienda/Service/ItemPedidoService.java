package com.unimag.Tienda.Service;

import com.unimag.Tienda.Dto.ItemPedidoDto;
import com.unimag.Tienda.Entidad.ItemPedido;
import com.unimag.Tienda.Entidad.Pedido;
import com.unimag.Tienda.Entidad.Producto;
import com.unimag.Tienda.Repository.ItemPedidoRepository;
import com.unimag.Tienda.Repository.PedidoRepository;
import com.unimag.Tienda.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ItemPedidoService {
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    private final PedidoRepository pedidoRepository;
    private  final ProductoRepository productoRepository;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository,PedidoRepository pedidoRepository,ProductoRepository productoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }
    public List<ItemPedido> buscarPorPedidoId(Long pedidoId) {
        return itemPedidoRepository.findByidPedido(pedidoId);
    }

    public List<ItemPedido> buscarPorProductoId(Long productoId) {
        return itemPedidoRepository.findByidProducto(productoId);
    }


    public Double CalcularTotalVentasProducto(Long idProducto){
        return itemPedidoRepository.findByidProducto(idProducto)
                .stream()
                .mapToDouble(ItemPedido -> ItemPedido.getCantidad() * ItemPedido.getPrecioUnitario())
                .sum();


    }
    public ItemPedido ActualizarItemPedido(Long id, ItemPedidoDto itemPedidoDto) {
        ItemPedido itemPedido = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemPedido no encontrado con ID: " + id));

        return itemPedidoRepository.save(itemPedido);
    }
    public ItemPedido crearItemPedido(ItemPedidoDto itemPedidoDto) {
        Pedido pedido = pedidoRepository.findById(itemPedidoDto.getIdPedido())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + itemPedidoDto.getIdPedido()));
        Producto producto =productoRepository.findById(itemPedidoDto.getIdProducto())
                .orElseThrow(()-> new RuntimeException("producto no encotrado con ID:"+itemPedidoDto.getIdProducto()));

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPedido(pedido);
        itemPedido.setProducto(producto);
        itemPedido.setCantidad(itemPedidoDto.getCantidad());
        itemPedido.setPrecioUnitario(itemPedidoDto.getPrecioUnitario());


        return itemPedidoRepository.save(itemPedido);
    }
    public void EliminarItemPedido(Long id) {
        itemPedidoRepository.deleteById(id);
    }
}
