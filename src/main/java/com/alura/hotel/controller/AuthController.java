package com.alura.hotel.controller;

import com.alura.hotel.domain.prueba.PrubeaDTO;
import com.alura.hotel.domain.prueba.Prueba;
import com.alura.hotel.domain.prueba.PruebaRepository;
import com.alura.hotel.domain.prueba.PruebaRespuesta;
import com.alura.hotel.domain.usuario.*;
import com.alura.hotel.infra.security.AuthService;
import com.alura.hotel.infra.security.DatosJWTToken;
import com.alura.hotel.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticacion", description = "obtiene el token para el usuario asignado que da " +
        "acceso al resto de endpoint")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PruebaRepository pruebaRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    @Operation(
            summary = "Registra Usuarios en la aplicación",
            description = "",
            tags = "")
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosUsuario datos) {
        var response = usuarioService.saveUser(datos);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    @Operation(
            summary = "inicia sesión en la aplicación",
            description = "",
            tags = "")
    public ResponseEntity<DatosJWTToken> autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario
                                                                   datosAutenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.username(),
                datosAutenticacionUsuario.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);

        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

    @PostMapping("/prueba")
    @Operation(
            summary = "inicia sesión en la aplicación",
            description = "",
            tags = "")
    public ResponseEntity<PruebaRespuesta> pruebas(@RequestBody @Valid PrubeaDTO datos) {
        Prueba prueba = pruebaRepository.save(new Prueba(datos));
        var response = new PruebaRespuesta(prueba);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/hello")
    @Operation(
            summary = "Prueba de funcionamiento",
            description = "",
            tags = {"hola", "get"})
    public ResponseEntity<String> hola() {
        return ResponseEntity.ok("Hola Mundo");
    }
}
