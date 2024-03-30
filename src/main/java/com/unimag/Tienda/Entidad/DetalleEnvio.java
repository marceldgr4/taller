package com.unimag.Tienda.Entidad;
import com.unimag.Tienda.Entidad.Enum.EstadoPedido;
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
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    private String direccion;
    private String Transportadora;
    private String NumeroGuia;
    private EstadoPedido estadoPedido;


}
