package com.unimag.Tienda.Service;

import com.unimag.Tienda.Dto.PedidoDto;
import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Entidad.Pedido;

import com.unimag.Tienda.Mapper.PedidoMapper;
import com.unimag.Tienda.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PedidoService {

@Autowired
    private PedidoRepository pedidoRepository;
    //------------CRUD--------------
    public Pedido CrearPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
    public Pedido OtenerPedidoPorID(Long idPedido){
        return pedidoRepository.findById(idPedido).orElse(null);
    }

    public Pedido ActualizarPedido(Pedido pedido) {
        if (pedido.getId() == null || !pedidoRepository.existsById(pedido.getId())) {
            throw new RuntimeException("El pedido no existe");
        }
        return pedidoRepository.save(pedido);
    }
    public void EliminarPedido(Long idPedido) {
        if (!pedidoRepository.existsById(idPedido)) {
            throw new RuntimeException("El pedido no existe");
        }
        pedidoRepository.deleteById(idPedido);
    }
    //----------------------------------
    //buscar pedidos entre dos fecha
    public List<Pedido> BuscarPedidoEntreFecha(LocalDateTime startDate, LocalDateTime endDate){
        return pedidoRepository.findByFechaPedidoBetween(startDate,endDate);
    }
    //Buscar pedidos por cliente y un estado
    public List<Pedido> BuscarPedidoPorClienteYEstado(Cliente cliente, String EstadoPedido){
        return pedidoRepository.findByClienteAndStatus(cliente, EstadoPedido);
    }
    //recuperar pedidos con sus artículos usando JOIN fetch para evitar el
    //problema N+1, para un cliente específico

    public List<Pedido>RecuperarPedidoConArticuloPorCliente(Long idCLiente){
        return pedidoRepository.findByClienteIdWithItemPedido(idCLiente);
    }



    public Optional<Pedido> BuscarPedidosPorCliente(Long idCliente) {
        return pedidoRepository.findById(idCliente);
    }
    public Pedido buscarPedidoPorId(Long idpedido) {

        Optional<Pedido> optionalPedido = pedidoRepository.findById(idpedido);
        return optionalPedido.orElse(null);
    }
    //------DTO----

    private PedidoMapper pedidoMapper;
    public PedidoDto CrearPedido(PedidoDto pedidoDto){
        Pedido pedido = pedidoMapper.pedidoDtoToPedido(pedidoDto);
        return pedidoMapper.pedidoToPedidoDto(pedido);
    }
    public PedidoDto ObtenerPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        return pedido != null ? pedidoMapper.pedidoToPedidoDto(pedido) : null;
    }
    public List<PedidoDto> buscarPedidosPorCliente(Long cliente) {
        List<Pedido> pedidos = pedidoRepository.findByClienteIdWithItemPedido(cliente);
        return pedidos.stream()
                .map(pedidoMapper::pedidoToPedidoDto)
                .collect(Collectors.toList());
    }

    public List<PedidoDto> buscarPedidosPorEstado(Cliente cliente, String estado) {
        List<Pedido> pedidos = pedidoRepository.findByClienteAndStatus(cliente,estado);
        return pedidos.stream()
                .map(pedidoMapper::pedidoToPedidoDto)
                .collect(Collectors.toList());
    }

    public PedidoDto ActualizarPedido(Long id, PedidoDto pedidoDto) {
        Pedido pedido = pedidoMapper.pedidoDtoToPedido(pedidoDto);
        pedido = pedidoRepository.save(pedido);
        return pedidoMapper.pedidoToPedidoDto(pedido);
    }

    public List<PedidoDto> ObtenerTodoLosPedidos() {
        List<Pedido>pedidos=pedidoRepository.findAll();
        return pedidos.stream().map(pedidoMapper::pedidoToPedidoDto).collect(Collectors.toList());
    }
}
