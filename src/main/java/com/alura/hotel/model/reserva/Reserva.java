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
import java.time.LocalDate;
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
    private LocalDate registro;
    private LocalDate checkIn;
    private LocalDate checkOut;
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

    public Reserva(DatosRegistroReserva datos, Usuario usuario, Habitacion habitacion, TipoPago tipoPago) {
        this.checkIn = datos.checkIn();
        this.checkOut = datos.checkOut();
        this.total = datos.total();
        this.usuario = usuario;
        this.habitacion = habitacion;
        this.tipoPago = tipoPago;
    }
}