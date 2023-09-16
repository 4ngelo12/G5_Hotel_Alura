package com.alura.hotel.model.reserva;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DatosListaReserva(String codReserva, LocalDateTime fechaReserva, LocalDateTime checkIn, LocalDateTime checkOut, BigDecimal total,
                                Long idUsuario, Long idHabitacion, Long idTipoPago) {
    public DatosListaReserva(Reserva reserva){
        this(reserva.getCodReserva(), reserva.getFechaReserva(), reserva.getCheckIn(), reserva.getCheckOut(),
                reserva.getTotal(), reserva.getUsuario().getId(), reserva.getHabitacion().getId(),
                reserva.getTipoPago().getId());
    }
}
