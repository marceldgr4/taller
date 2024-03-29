package com.unimag.Tienda.Entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import  java.time.LocalDateTime;
import  java.time.LocalTime;
import  java.util.List;


@Entity
@Table(name = "Pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_Cliente",referencedColumnName = "id",nullable = false)
    private Cliente cliente;
    @OneToMany(mappedBy = "Pedido",cascade = CascadeType.ALL)
    private List<ItemPedido> itemPedidos;

    private LocalDateTime fecha_Pedido;
    private  String status;
}
