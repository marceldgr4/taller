package com.unimag.Tienda.Repository;

import com.unimag.Tienda.Entidad.DetalleEnvio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleEnvioRepository extends JpaRepository<DetalleEnvio,Long> {
List<DetalleEnvio>findByidPedido(Long idPedido);
List<DetalleEnvio> findByTransportadora(String Trasnsportadora);
@Query("select de from DetalleEnvio de where de.estadoPedido = :estadoPedido")
    List<DetalleEnvio>findByEstadoPedido(@Param("estadoPedido")String estadoPedido);
}
