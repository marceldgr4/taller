package com.unimag.Tienda.Repository;
import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
