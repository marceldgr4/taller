package com.unimag.Tienda.ServiceTest;

import com.unimag.Tienda.Dto.ItemPedidoDto;
import com.unimag.Tienda.Entidad.Pedido;
import com.unimag.Tienda.Repository.ItemPedidoRepository;
import com.unimag.Tienda.Repository.PedidoRepository;
import com.unimag.Tienda.Service.ItemPedidoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemPedidoServiceTest {
    @Mock
    private ItemPedidoRepository itemPedidoRepository;
    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private ItemPedidoService itemPedidoService;
    @Test
    public void testCrearItemPedido(){
        ItemPedidoDto itemPedidoDto =new ItemPedidoDto();
        Pedido pedido =new Pedido();
        pedido.setId(pedido.getId());
        when(pedidoRepository.findById(pedido.getId()));
        itemPedidoService.crearItemPedido(itemPedidoDto);
    }
}
