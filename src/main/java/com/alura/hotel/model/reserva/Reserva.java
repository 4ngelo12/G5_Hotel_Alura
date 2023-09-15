package com.alura.hotel.model.reserva;

import com.alura.hotel.model.formatoPago.TipoPago;
import com.alura.hotel.model.habitacion.Habitacion;
import com.alura.hotel.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "reservas")
@Entity(name = "Reserva")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codReserva = String.valueOf(UUID.randomUUID());
    private LocalDateTime checkIn = LocalDateTime.now();
    private LocalDateTime checkOut;
    private BigDecimal total;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formpago_id")
    private TipoPago tipoPago;

    public Reserva(LocalDateTime checkOut, BigDecimal total, Usuario usuario,
                   Habitacion habitacion, TipoPago tipoPago) {
        this.checkOut = checkOut;
        this.total = total;
        this.usuario = usuario;
        this.habitacion = habitacion;
        this.tipoPago = tipoPago;
    }
}