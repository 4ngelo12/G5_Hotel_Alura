package com.alura.hotel.model.reserva;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DatosRespuestaReserva(Long id, String codReserva, LocalDate checkIn, LocalDate checkOut, BigDecimal total,
                                    Long usuarioId, Long habitacionId, Long tipoPagoId) {
        public DatosRespuestaReserva(Reserva reserva) {
                this(reserva.getId(), reserva.getCodReserva(), reserva.getCheckIn(), reserva.getCheckOut(), reserva.getTotal(),
                        reserva.getUsuario().getId(), reserva.getHabitacion().getId(), reserva.getTipoPago().getId());
        }
}
