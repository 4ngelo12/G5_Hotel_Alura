package com.alura.hotel.model.reserva;

import jakarta.validation.constraints.NotNull;

public record DatosCancelarReserva(
        @NotNull
        Long id,
        @NotNull
        MotivoCancelamiento motivoCancelamiento
) {
}
