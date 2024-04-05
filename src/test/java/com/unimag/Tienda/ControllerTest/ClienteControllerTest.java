package com.unimag.Tienda.ControllerTest;
import com.unimag.Tienda.Dto.ClienteDto;
import com.unimag.Tienda.Service.ClienteService;
import  com.unimag.Tienda.api.v1.Customers.ClienteController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class ClienteControllerTest {
    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testObtenerClientePorId() {
        Long id = 1L;
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(id);
        when(clienteService.ObtenerClientePorID(id)).thenReturn(clienteDto);

        ResponseEntity<ClienteDto> response = clienteController.ObtenerClientePorId(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, Objects.requireNonNull(response.getBody()).getId());
        verify(clienteService, times(1)).ObtenerClientePorID(id);

    }
@Test
    void testObtenerTodosClientes() {
        List<ClienteDto> clienteDto = new ArrayList<>();
        clienteDto.add(new ClienteDto());
        clienteDto.add(new ClienteDto());
        when(clienteService.ObtenerTodoLosClientes()).thenReturn(clienteDto);
        ResponseEntity<List<ClienteDto>> response =clienteController.ObtenerTodoLosClientes();


    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(2, Objects.requireNonNull(Objects.requireNonNull(response.getBody())).size());
    verify(clienteService, times(1)).ObtenerTodoLosClientes();



    }


}
