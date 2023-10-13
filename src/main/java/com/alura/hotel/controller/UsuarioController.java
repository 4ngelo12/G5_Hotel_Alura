package com.alura.hotel.controller;

import com.alura.hotel.model.service.UsuarioService;
import com.alura.hotel.model.usuario.DatosActualizarUsuario;
import com.alura.hotel.model.usuario.DatosRespuestaActualizarUsuario;
import com.alura.hotel.model.usuario.DatosRespuestaUsuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Usuario", description = "Opciones de usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    @Operation(
            summary = "Obtener datos del usuario",
            description = "",
            tags = {"get"})
    public DatosRespuestaUsuario obtenerUsuario(@RequestHeader("Authorization") String token) {
        var usuario = usuarioService.getUser(token);
        return new DatosRespuestaUsuario(usuario);
    }

    @PutMapping
    @Transactional
    @Operation(
            summary = "Actualizar los datos del cliente",
            description = "",
            tags = {"put"})
    public ResponseEntity<DatosRespuestaActualizarUsuario> actualizarUsuario(
            @RequestBody @Valid DatosActualizarUsuario datos) {
        var usuario = usuarioService.updateUser(datos);
        return ResponseEntity.ok(usuario);
    }
}
