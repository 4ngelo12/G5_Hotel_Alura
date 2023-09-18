package com.alura.hotel.model.usuario;

public record DatosRespuestaUsuario(Long id, String nombre, String apellido, String documento,
                                    String telefono, Long idRole, String username) {
    public DatosRespuestaUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getDocumento(),
                usuario.getTelefono(), usuario.getRole().getId(), usuario.getUsername());
    }
}
