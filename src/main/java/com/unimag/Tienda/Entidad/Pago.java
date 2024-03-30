package com.unimag.Tienda.Entidad;
import com.unimag.Tienda.Entidad.Enum.EstadoPago;
import com.unimag.Tienda.Entidad.Enum.MetodoPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "pagos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    private Double totalPago;
    private LocalDateTime fechaPago;
    private MetodoPago metodoPago;
    private String numeroTransaccion;
    private EstadoPago estadoPago;

}
