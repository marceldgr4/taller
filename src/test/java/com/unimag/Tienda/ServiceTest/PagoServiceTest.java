package com.unimag.Tienda.ServiceTest;

import com.unimag.Tienda.Dto.PagoDto;
import com.unimag.Tienda.Entidad.Enum.EstadoPago;
import com.unimag.Tienda.Entidad.Pago;
import com.unimag.Tienda.Mapper.PagoMapper;
import com.unimag.Tienda.Repository.PagoRepository;
import com.unimag.Tienda.Service.PagoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PagoServiceTest {
    @Mock
    private PagoRepository pagoRepository;

    @Mock
    private PagoMapper pagoMapper;

    @InjectMocks
    private PagoService pagoService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);

    }
    @Test
    public void testCrearPago(){
        PagoDto pagoDto =new PagoDto();
        pagoDto.setFechaPago(LocalDateTime.now());
        pagoDto.setEstadoPago(EstadoPago.APROBADO);
        pagoDto.setTotalPago(100.0);
        Pago pago= new Pago();
        pago.setId(pago.getId());

        when(pagoMapper.pagoDtoToPago(any(PagoDto.class))).thenReturn(pago);
        when(pagoRepository.save(any(Pago.class))).thenReturn(pago);
        when(pagoMapper.pagoToPagoDto(any(Pago.class))).thenReturn(pagoDto);
        PagoDto result=pagoService.CrearPago(pagoDto);
        assertEquals(pagoDto,result);

    }
    @Test
    public void testObtenerTodoLosPagos(){
        List<Pago> pagos = Arrays.asList();
        List<PagoDto> expected =Arrays.asList();
        when(pagoRepository.findAll()).thenReturn(pagos);
        List<PagoDto> result = pagoService.ObtenerTodoLosPagos();
        assertEquals(expected, result);
    }

}
