package com.unimag.Tienda.Entidad;

import jakarta.persistence.*;
import  lombok.*;

import java.util.List;

@Entity
@Table(name = "Clientes" )
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class Cliente {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private  String Nombre;
    private  String Email;
    private  String Direccion;

    @OneToMany(mappedBy = "Cliente")
    private List<Pedido> Pedidos;
    @ManyToMany
    @JoinTable(
            name = "Cliente_Producto",
            joinColumns = @JoinColumn(name = "id_Cliente", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_Producto",referencedColumnName = "id")    )
private List<Producto>productos;

}
