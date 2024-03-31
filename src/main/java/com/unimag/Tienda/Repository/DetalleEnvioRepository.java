package com.unimag.Tienda.Repository;

import com.unimag.Tienda.Entidad.DetalleEnvio;
import com.unimag.Tienda.Entidad.Enum.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetalleEnvioRepository extends JpaRepository<DetalleEnvio,Long> {
Optional<DetalleEnvio> findByidPedido(Long idPedido);
List<DetalleEnvio> findByTransportadora(String Transportadora);
@Query("select de from DetalleEnvio de where de.estadoPedido = :estadoPedido")
    List<DetalleEnvio>findByEstadoPedido(String EstadoPedido);
}
