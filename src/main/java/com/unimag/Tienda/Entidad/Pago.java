package com.unimag.Tienda.Entidad;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
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

    @OneToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    private double TotalPago;
    private LocalDateTime fechaPago;
    private MetodoPago metodoPago;

}
