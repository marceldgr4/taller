package com.unimag.Tienda.Entidad;

import com.unimag.Tienda.Entidad.Enum.EstadoPedido;
import jakarta.persistence.*;
import lombok.*;

import  java.time.LocalDateTime;

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
    private LocalDateTime fechaPedido;
    private Double total;
    @Enumerated(EnumType.STRING)
    private EstadoPedido status;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itemPedidos;
    @OneToOne(mappedBy = "pago")
    private Pago pago;
}
/*
@OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL)
    private List<ItemPedido> itemPedidos = new ArrayList<>();

    @OneToOne(mappedBy = "pedido",cascade = CascadeType.ALL)
    private Pago pago;

    @OneToOne(mappedBy = "pedido",cascade = CascadeType.ALL)
    private DetalleEnvio detalleEnvio;
* */