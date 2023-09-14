package com.alura.hotel.controller;

import com.alura.hotel.infra.errores.ValidacionDeIntegridad;
import com.alura.hotel.model.habitacion.DatosRegistroHabitacion;
import com.alura.hotel.model.habitacion.DatosRespuestaHabitacion;
import com.alura.hotel.model.service.HabitacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/habitaciones")
@EnableMethodSecurity(securedEnabled = true)
@SecurityRequirement(name = "bearer-key")
@Secured("ADMIN")
@Tag(name = "Habitaciones", description = "Permite realizar las operaciones para gestionar las habitaciones")
public class HabitacionController {
    @Autowired
    private HabitacionService habitacionService;

    @PostMapping
    @Operation(
            summary = "Registra las habitaciones en la base de datos",
            description = "",
            tags = {"post"})
    public ResponseEntity<DatosRespuestaHabitacion> registrarHabitacion(@RequestBody @Valid DatosRegistroHabitacion datos)
            throws ValidacionDeIntegridad {
        var response = habitacionService.registrarHabitacion(datos);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<String> obtenerDatos() {
        return ResponseEntity.ok("Hola Administrador");
    }
}
