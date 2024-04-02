package com.unimag.Tienda.ServiceTest;

import com.unimag.Tienda.Dto.PedidoDto;
import com.unimag.Tienda.Entidad.Pedido;
import com.unimag.Tienda.Repository.PedidoRepository;
import com.unimag.Tienda.Service.PedidoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {
    @Mock
    private PedidoRepository pedidoRepository;
    @InjectMocks
    private PedidoService pedidoService;

    @Test
    public void testCrearPedido() {
        // Configurar el pedido de prueba
        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setIdCliente(pedidoDto.getIdCliente());
        pedidoDto.setStatus("PENDIENTE");

        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> {
            Pedido pedido = invocation.getArgument(0);
            pedido.setId(pedido.getId());
            return pedido;
        });

    }


}