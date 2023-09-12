package com.alura.hotel.domain.usuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public record DatosUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        String apellido,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String documento,
        @NotBlank
        String telefono,
        @NotBlank
        String role,
        @NotBlank
        String password
) {}
