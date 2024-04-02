package com.unimag.Tienda.Unit.RepositoryTest;

import com.unimag.Tienda.Entidad.Enum.EstadoPedido;
import com.unimag.Tienda.Entidad.Pedido;
import com.unimag.Tienda.Repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class PedidoRepositoryTest {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Test
    public void testCrearPedido(){
        Pedido pedido = new Pedido();
        pedido.setCliente("cliente de prueba");
        pedido.setStatus(EstadoPedido.PENDIENTE);

        pedidoRepository.save(pedido);

        assertThat(pedido.getId()).isNotNull();
    }
    @Test
    public void testBuscarPedidosPorEstado() {
        Pedido pedido1 = new Pedido();
        pedido1.setCliente("Cliente 1");
        pedido1.setStatus(EstadoPedido.PENDIENTE);
        pedidoRepository.save(pedido1);
        List<Pedido>EstadoDeLosPedidos = pedidoRepository.findByEstadoPedido(EstadoPedido.PENDIENTE);
        assertThat(EstadoDeLosPedidos).isNotNull();
    }

}


