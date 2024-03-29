package com.unimag.Tienda.Repository;
import com.unimag.Tienda.Entidad.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    List<Cliente> findByEmail(String email);
    List<Cliente> findByDireccion(String Direccion);
    List<Cliente> findByNombreStartingWithIgnoreCase(String Nombre);


}
