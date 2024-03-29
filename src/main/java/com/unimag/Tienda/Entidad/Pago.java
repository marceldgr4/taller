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
    @ManyToOne
    @JoinColumn(name = "id_Pedido" ,referencedColumnName = "id",nullable = false)
    private Pedido pedido;

    private BigDecimal total_Pago;
    private  LocalDateTime fecha_Pago;
    @Enumerated(EnumType.STRING)
    private MetodoPago Metodo_Pago;
}
