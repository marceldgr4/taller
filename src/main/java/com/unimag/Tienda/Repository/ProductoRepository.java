package com.unimag.Tienda.Repository;

import com.unimag.Tienda.Entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
    List<Producto> findByNombreContainingIgnoreCase(String term);
    List<Producto> findByStockGreaterThan(Integer Cantidad);
    List<Producto> findByPriceLessThanAndStockLessThanEqual(Double Precio, Integer Cantidad);


}
