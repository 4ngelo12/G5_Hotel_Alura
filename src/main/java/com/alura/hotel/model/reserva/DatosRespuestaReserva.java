package com.alura.hotel.model.reserva;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DatosRespuestaReserva(Long id, String codReserva, LocalDateTime checkIn, LocalDateTime checkOut, BigDecimal total,
                                    Long usuarioId, Long habitacionId, Long tipoPagoId) {
        public DatosRespuestaReserva(Reserva reserva) {
                this(reserva.getId(), reserva.getCodReserva(), reserva.getCheckIn(), reserva.getCheckOut(), reserva.getTotal(),
                        reserva.getUsuario().getId(), reserva.getHabitacion().getId(), reserva.getTipoPago().getId());
        }
}
