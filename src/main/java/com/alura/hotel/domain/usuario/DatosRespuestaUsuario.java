package com.alura.hotel.domain.usuario;

public record DatosRespuestaUsuario(Long id, String nombre, String apellido, String email, String documento,
                                    String telefono, String username) {
    public DatosRespuestaUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getDocumento(),
                usuario.getTelefono(), usuario.getUsername());
    }
}
