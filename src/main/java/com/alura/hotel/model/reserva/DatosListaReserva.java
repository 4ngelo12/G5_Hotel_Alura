package com.alura.hotel.model.reserva;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DatosListaReserva(String codReserva, LocalDateTime fechaReserva, LocalDateTime checkIn, LocalDateTime checkOut, BigDecimal total,
                                Integer numHabitacion, String tipoPago) {
    public DatosListaReserva(Reserva reserva){
        this(reserva.getCodReserva(), reserva.getFechaReserva(), reserva.getCheckIn(), reserva.getCheckOut(),
                reserva.getTotal(), reserva.getHabitacion().getNumero(),
                reserva.getTipoPago().getNombre());
    }
}
