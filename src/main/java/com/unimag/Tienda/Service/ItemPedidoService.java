package com.unimag.Tienda.Service;

import com.unimag.Tienda.Entidad.ItemPedido;
import com.unimag.Tienda.Repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    public Double calcularTatolaVentasProducto(Long idProducto){
        return itemPedidoRepository.findByidProducto(idProducto)
                .stream()
                .mapToDouble(ItemPedido -> ItemPedido.getCantidad() * ItemPedido.getPrecioUnitario())
                .sum();


    }
}
