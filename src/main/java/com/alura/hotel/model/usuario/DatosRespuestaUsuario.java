package com.alura.hotel.model.usuario;

import java.time.LocalDate;

public record DatosRespuestaUsuario(Long id, String nombre, String apellido, LocalDate fechaNacimiento,
                                    TipoDocumento tipoDocumento, String documento, String telefono,
                                    String role, String username) {
    public DatosRespuestaUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getFechaNacimiento(),
                usuario.getTipoDocumento(), usuario.getDocumento(),
                usuario.getTelefono(), usuario.getRole().getNombre(), usuario.getUsername());
    }
}
