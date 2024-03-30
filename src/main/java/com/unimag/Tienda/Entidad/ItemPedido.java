package com.unimag.Tienda.Entidad;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "itemPedidos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    private Integer cantidad;
    private  Double precioUnitario;
    private  Double total;


}
