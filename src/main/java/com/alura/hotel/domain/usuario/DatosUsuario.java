package com.alura.hotel.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        String apellido,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,9}")
        String documento,
        @NotNull
        Role role,
        @NotBlank
        String username,
        @NotBlank
        String password
        ) {}
