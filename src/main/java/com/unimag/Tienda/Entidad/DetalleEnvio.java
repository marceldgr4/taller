package com.unimag.Tienda.Entidad;
import  jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalleEnvio")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class DetalleEnvio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "id_pedido", referencedColumnName = "id",unique = true, nullable = false)
    private Pedido pedido;

    private String Direccion;
    private String Transporte;
    private  String Numero_Guia;

}
