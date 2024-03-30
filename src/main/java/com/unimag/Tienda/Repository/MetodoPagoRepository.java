package com.unimag.Tienda.Repository;

import com.unimag.Tienda.Entidad.Enum.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago,Long> {
}
