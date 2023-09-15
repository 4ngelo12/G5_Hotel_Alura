package com.alura.hotel.model.habitacion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroHabitacion(
        @NotNull
        Integer numero,
        @NotBlank
        String descripcion,
        @NotNull
        Integer numCamas,
        @NotNull
        Double precio,
        @NotNull
        Long idTipoHabitacion
) {
}
