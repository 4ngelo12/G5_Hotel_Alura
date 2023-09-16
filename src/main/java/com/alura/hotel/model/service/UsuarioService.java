package com.alura.hotel.model.service;

import com.alura.hotel.infra.errores.ValidacionDeIntegridad;
import com.alura.hotel.infra.security.TokenService;
import com.alura.hotel.model.repository.RoleRepository;
import com.alura.hotel.model.repository.UsuarioRepository;
import com.alura.hotel.model.usuario.DatosRespuestaUsuario;
import com.alura.hotel.model.usuario.DatosRegistroUsuario;
import com.alura.hotel.model.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


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

    public DatosRespuestaUsuario saveUser(DatosRegistroUsuario datosRegistroUsuario) {
        if (!roleRepository.findById(datosRegistroUsuario.idRole()).isPresent()) {
            throw new ValidacionDeIntegridad("El rol ingresado no existe");
        }
        var role = roleRepository.findById(datosRegistroUsuario.idRole()).get();

        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario, role, passwordEncoder));

        return new DatosRespuestaUsuario(usuario);
    }

    public Usuario getUser(String token) {
        var jwtToken = token.replace("Bearer ", "");
        var nombreUsuario = tokenService.getSubject(jwtToken);
        var usuario = usuarioRepository.getUserData(nombreUsuario);

        if (usuario.getRole().getNombre().equals("EMPLEADO")) {
            throw new ValidacionDeIntegridad("No está autorizado para realizar esta acción");
        }

        return usuario;
    }
}
