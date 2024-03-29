package com.unimag.Tienda.Repository;
import com.unimag.Tienda.Entidad.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {
}
