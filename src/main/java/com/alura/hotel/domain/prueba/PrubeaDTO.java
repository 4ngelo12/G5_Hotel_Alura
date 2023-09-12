package com.alura.hotel.domain.prueba;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PrubeaDTO(
        @NotBlank
        String nombre,
        @NotBlank
        String apel,
        @NotNull
        Integer numero
) {
}
