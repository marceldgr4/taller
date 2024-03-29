package com.unimag.Tienda.Repository;

import com.unimag.Tienda.Entidad.DetalleEnvio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleEnvioRepository extends JpaRepository<DetalleEnvio,Long> {
}
