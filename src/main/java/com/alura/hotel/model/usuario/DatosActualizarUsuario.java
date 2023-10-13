package com.alura.hotel.model.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record DatosActualizarUsuario(
        @NotNull
        Long id,
        @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚüÜñÑ ]{1,45}+$", message = "El nombre solo puede tener 45 caracteres")
        String nombre,
        @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚüÜñÑ ]{1,45}+$", message = "El nombre solo puede tener 45 caracteres")
        String apellido,
        LocalDate fechaNacimiento,
        TipoDocumento tipoDocumento,
        @Pattern(regexp = "\\d{8,15}")
        String documento,
        @Pattern(regexp = "\\d{9,15}", message = "El número de teléfono debe tener al menos 9 caracteres")
        String telefono,
        String role
) {}
