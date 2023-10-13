package com.alura.hotel.model.service;

import com.alura.hotel.infra.errores.ValidacionDeIntegridad;
import com.alura.hotel.infra.security.TokenService;
import com.alura.hotel.model.repository.RoleRepository;
import com.alura.hotel.model.repository.UsuarioRepository;
import com.alura.hotel.model.usuario.*;
import com.alura.hotel.model.usuario.validaciones.ValidacionUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    List<ValidacionUsuario> validadores;

    public DatosRespuestaUsuario saveUser(DatosRegistroUsuario datosRegistroUsuario) {
        if (!roleRepository.findById(datosRegistroUsuario.idRole()).isPresent()) {
            throw new ValidacionDeIntegridad("El rol ingresado no existe");
        }

        validadores.forEach(v -> v.validar(datosRegistroUsuario));

        var role = roleRepository.findById(datosRegistroUsuario.idRole()).get();
        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario, role, passwordEncoder));

        return new DatosRespuestaUsuario(usuario);
    }

    public DatosRespuestaActualizarUsuario updateUser(DatosActualizarUsuario datos) {
        Usuario usuario = usuarioRepository.getReferenceById(datos.id());
        usuario.actualizarDatos(datos);

        return new DatosRespuestaActualizarUsuario(usuario);
    }

    public Usuario getUser(String token) {
        var jwtToken = token.replace("Bearer ", "");
        var nombreUsuario = tokenService.getSubject(jwtToken);

        return usuarioRepository.getUserData(nombreUsuario);
    }
}
