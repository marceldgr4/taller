package com.unimag.Tienda.Repository;

import com.unimag.Tienda.Entidad.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago,Long> {
    //Recuperar pagos dentro de un rango de fecha:
    @Query("select p from Pago p where p.fechaPago between :startDate and :endDate")
    List<Pago> findByFechaPagoBetween(@Param("startDate")LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    //Recuperar pagos por un identificador de una orden y método de pago:
    List<Pago> findByPedidoIdAndMetodoPago(Long idPedido, String metodoPago);

}
