package com.unimag.Tienda.Service;

import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Entidad.Pedido;
import com.unimag.Tienda.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoServicio {
    @Autowired
    private PedidoRepository pedidoRepository;
    public List<Pedido>BuscarPedidoEntreFecha(LocalDateTime startDate, LocalDateTime endDate){
        return pedidoRepository.findByFechaPedidoBetween(startDate,endDate);
    }
    public List<Pedido>RecuperarPedidoConArticuloPorCliente(Cliente cliente){
        return pedidoRepository.findByClienteWithItemPedido(cliente);
    }
}
