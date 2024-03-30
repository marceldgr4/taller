package com.unimag.Tienda.Repository;

import com.unimag.Tienda.Entidad.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido,Long> {
    //Buscar Items del pedido por Pedido Id:
    List<ItemPedido>findByidPedido(Long idPedido);
    //Buscar items del pedido para un producto específico:
    List<ItemPedido> findByidProducto(Long idProducto);

    //Calcular la suma del total de ventas para un producto, utilizando la agregación SUM:
    @Query("select sum (ip.cantidad * ip.precioUnitario)from ItemPedido ip where ip.producto.id= :idProducto")
    Double CalcularTotalVentasPorProducto(@Param("idProducto") Long idProducto);

}
