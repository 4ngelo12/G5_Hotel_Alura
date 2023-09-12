package com.alura.hotel.domain.usuario;

import com.alura.hotel.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public DatosRespuestaUsuario saveUser(DatosUsuario datosUsuario) {
        Usuario usuario = usuarioRepository.save(new Usuario(datosUsuario, passwordEncoder));

        return new DatosRespuestaUsuario(usuario);
    }
}
