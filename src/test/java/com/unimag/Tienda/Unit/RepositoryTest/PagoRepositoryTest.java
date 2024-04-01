package com.unimag.Tienda.Unit.RepositoryTest;

import com.unimag.Tienda.Entidad.Enum.MetodoPago;
import com.unimag.Tienda.Entidad.Pago;
import com.unimag.Tienda.Repository.PagoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@DataJpaTest

public class PagoRepositoryTest {
    @Autowired
    private PagoRepository pagoRepository;
    @Test
    public void testGuardarYRecuperarPago(){
        Pago pago =new Pago();
        pago.setId(pago.getId());
        pago.setFechaPago(LocalDateTime.now());
        pago.setMetodoPago(MetodoPago.PSE);
        Pago pagoGuardado =pagoRepository.save(pago);
        Optional<Pago> pagoRecuperado =pagoRepository.findById(pagoGuardado.getId());
        assertEquals(pagoGuardado,pagoRecuperado);
    }
    @Test
    public void testRecuperarPagosPorMetodoPago() {
        Pago pago1 = new Pago();
        pago1.setTotalPago(50.0);
        pago1.setFechaPago(LocalDateTime.now());
        pago1.setMetodoPago(MetodoPago.EFECTIVO);
        pagoRepository.save(pago1);

        Pago pago2 = new Pago();
        pago2.setTotalPago(75.0);
        pago2.setFechaPago(LocalDateTime.now());
        pago2.setMetodoPago(MetodoPago.TARJETA_CREDITO);
        pagoRepository.save(pago2);
        List<Pago> pagosPorTarjeta = pagoRepository.findByPedidoIdAndMetodoPago(pago1.getId(), MetodoPago.TARJETA_CREDITO);

    }
}
