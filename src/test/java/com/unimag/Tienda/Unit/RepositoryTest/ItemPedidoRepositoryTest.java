package com.unimag.Tienda.Unit.RepositoryTest;

import com.unimag.Tienda.Entidad.ItemPedido;

import com.unimag.Tienda.Repository.ItemPedidoRepository;
import com.unimag.Tienda.Service.ItemPedidoService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ItemPedidoRepositoryTest {
    @Mock
    private ItemPedidoRepository itemPedidoRepository;
    @InjectMocks
    private ItemPedidoService itemPedidoService;

    @Test
    public void testBuscarPorIdPedido(){
        ItemPedido itemPedido =new ItemPedido();
        itemPedido.setId(itemPedido.getId());
        List<ItemPedido> itemPedidoList = List.of();

    }
}
