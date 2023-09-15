package com.alura.hotel.model.reserva;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroReserva(
        @NotNull
        LocalDateTime checkOut,
        @NotNull
        Long habitacionId,
        @NotNull
        Long tipoPagoId
) {
}
