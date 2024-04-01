package com.unimag.Tienda.Repository;

import com.unimag.Tienda.Entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    @Query("select p from Producto p where lower(p.nombreProducto) like %:searchTerm%")
    List<Producto> findByNombreContainingIgnoreCase(String term);
    @Query("select p from Producto p where p.stock>0")
    List<Producto> findByStockGreaterThan(Integer Cantidad);

    @Query("select p from Producto p where p.precio <=:maxPrecio and p.stock<=:maxStock")
    List<Producto> findByPriceLessThanAndStockLessThanEqual(Double Precio, Integer Stock);


}
