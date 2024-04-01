package com.unimag.Tienda.Entidad;

import jakarta.persistence.*;
import  lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes" )
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class Cliente {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private  String Nombre;
    private  String Email;
    private  String Direccion;
    private String CityName;

    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    private List<Pedido> pedidos =new ArrayList<>();



}
