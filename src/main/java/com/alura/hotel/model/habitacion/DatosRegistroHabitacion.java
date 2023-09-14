package com.alura.hotel.model.habitacion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DatosRegistroHabitacion(
        @NotNull
        Integer numero,
        @NotBlank
        String descripcion,
        @NotNull
        Integer numCamas,
        @NotNull
        BigDecimal precio,
        @NotNull
        Long idTipoHabitacion
) {
}
