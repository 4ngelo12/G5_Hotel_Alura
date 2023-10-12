package com.alura.hotel.controller;

import com.alura.hotel.model.service.UsuarioService;
import com.alura.hotel.model.usuario.DatosActualizarUsuario;
import com.alura.hotel.model.usuario.DatosRespuestaActualizarUsuario;
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
