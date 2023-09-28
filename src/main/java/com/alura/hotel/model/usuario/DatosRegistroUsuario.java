package com.alura.hotel.model.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record DatosRegistroUsuario(
        @NotBlank
        @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚüÜñÑ ]{1,45}+$", message = "El nombre solo puede tener 45 caracteres")
        String nombre,
        @NotBlank
        @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚüÜñÑ ]{1,45}+$", message = "El nombre solo puede tener 45 caracteres")
        String apellido,
        @NotBlank
        @Email
        String email,
        @NotNull
        LocalDate fechaNacimiento,
        @NotNull
        TipoDocumento tipoDocumento,
        @NotBlank
        @Pattern(regexp = "\\d{8,15}")
        String documento,
        @NotBlank
        @Pattern(regexp = "\\d{9,15}", message = "El número de teléfono debe tener al menos 9 caracteres")
        String telefono,
        @NotNull
        Long idRole,
        @NotBlank
        String password
) {}
