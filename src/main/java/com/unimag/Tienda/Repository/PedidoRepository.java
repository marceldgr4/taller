package com.unimag.Tienda.Repository;
import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Entidad.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    @Query("select p from Pedido  p where p.fechaPedido between :startDate and :endDate")
    List<Pedido> findByFechaPedidoBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    List<Pedido>findByClienteAndStatus(Cliente cliente, String status);

    @Query("select distinct p from Pedido p join fetch p.itemPedidos where p.cliente = :cliente")
    List<Pedido>findByClienteWithItemPedido(@Param("cliente")Cliente cliente);
}
