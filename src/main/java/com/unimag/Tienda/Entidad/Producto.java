package com.unimag.Tienda.Entidad;

import jakarta.persistence.*;
import  lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "productos" )
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter


public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreProducto;
    private Double precio;
    private Integer stock;


   @OneToMany(mappedBy = "producto")
   private List<ItemPedido> itemPedidos;



}
