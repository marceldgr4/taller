package com.unimag.Tienda.Service;

import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Entidad.Pedido;
import com.unimag.Tienda.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    public List<Pedido>BuscarPedidoEntreFecha(Date startDate, Date endDate){
        return pedidoRepository.findByFechaPedidoBetween(startDate,endDate);
    }
    public List<Pedido>RecuperarPedidoConArticuloPorCliente(Cliente cliente){
        return pedidoRepository.findByClienteWithItemPedido(cliente);
    }
    public Pedido actualizarPedido(Pedido pedido) {
        if (pedido.getId() == null || !pedidoRepository.existsById(pedido.getId())) {
            throw new RuntimeException("El pedido no existe");
        }
        return pedidoRepository.save(pedido);
    }
    public void EliminarPedido(Long pedidoId) {
        if (!pedidoRepository.existsById(pedidoId)) {
           throw new RuntimeException("El pedido no existe");
        }
        pedidoRepository.deleteById(pedidoId);
    }
    public Pedido GuardarPedido(Pedido pedido) {

        return pedidoRepository.save(pedido);
    }
    public Optional<Pedido> BuscarPedidosPorCliente(Long idCliente) {
        return pedidoRepository.findById(idCliente);
    }
    public Pedido buscarPedidoPorId(Long idpedido) {

        Optional<Pedido> optionalPedido = pedidoRepository.findById(idpedido);
        return optionalPedido.orElse(null);
    }
}
