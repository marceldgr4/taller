package com.unimag.Tienda.Repository;
import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Entidad.Enum.EstadoPedido;
import com.unimag.Tienda.Entidad.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    @Query("select p from Pedido  p where p.fechaPedido between :startDate and :endDate")
    List<Pedido> findByFechaPedidoBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    List<Pedido>findByClienteAndStatus(Cliente cliente, String status);

    @Query("select distinct p from Pedido p join fetch p.itemPedidos where p.cliente.id = :idCliente")
    List<Pedido>findByClienteIdWithItemPedido( Long cliente);


    List<Pedido> findByEstadoPedido(EstadoPedido estadoPedido);
}
