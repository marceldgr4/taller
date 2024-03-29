package com.unimag.Tienda.Entidad;

import jakarta.persistence.*;
import lombok.*;

import  java.time.LocalDateTime;

import java.util.ArrayList;
import  java.util.List;


@Entity
@Table(name = "pedidos")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter


public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL)
    private List<ItemPedido> itemPedidos = new ArrayList<>();

    @OneToOne(mappedBy = "pedido",cascade = CascadeType.ALL)
    private Pago pago;

    @OneToOne(mappedBy = "pedido",cascade = CascadeType.ALL)
    private DetalleEnvio detalleEnvio;


    private LocalDateTime fechaPedido;

    @Enumerated(EnumType.STRING)
    private  EstadoPedido status;
}
