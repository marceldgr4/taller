package com.unimag.Tienda.Entidad;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "itemPedido")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="idPedido", referencedColumnName = "id",nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idProducto", referencedColumnName = "id",nullable = false)
    private Producto producto;


    private int Cantidad;
    private BigDecimal PrecioUnidad;


}
