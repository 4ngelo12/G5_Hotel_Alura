package com.alura.hotel.model.reserva;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DatosRegistroReserva(
        @NotNull
        LocalDate checkIn,
        @NotNull
        LocalDate checkOut,
        @NotNull
        BigDecimal total,
        @NotNull
        Long habitacionId,
        @NotNull
        Long tipoPagoId
) {
}
