package com.unimag.Tienda.Entidad;
import jakarta.persistence.*;
import  lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Products" )
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String NameProduct;
    private BigDecimal price;
    private  int Stock;

    @ManyToMany(mappedBy = "productos")
    private List<ItemPedido>itemPedidos;



}
