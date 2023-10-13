package com.alura.hotel.model.usuario;

import java.time.LocalDate;

public record DatosRespuestaActualizarUsuario(Long id, String nombre, String apellido, LocalDate fechaNacimiento,
                                              TipoDocumento tipoDocumento, String documento, String telefono,
                                              String role) {
    public DatosRespuestaActualizarUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getFechaNacimiento(),
                usuario.getTipoDocumento(), usuario.getDocumento(),
                usuario.getTelefono(), usuario.getRole().getNombre());
    }
}
