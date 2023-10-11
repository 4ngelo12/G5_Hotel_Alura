package com.alura.hotel.controller;

import com.alura.hotel.infra.errores.ValidacionDeIntegridad;
import com.alura.hotel.model.habitacion.DatosListaHabitacion;
import com.alura.hotel.model.habitacion.DatosRegistroHabitacion;
import com.alura.hotel.model.habitacion.DatosRespuestaHabitacion;
import com.alura.hotel.model.service.HabitacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/habitaciones")
@EnableMethodSecurity(securedEnabled = true)
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Habitaciones", description = "Permite realizar las operaciones para gestionar las habitaciones")
public class HabitacionController {
    @Autowired
    private HabitacionService habitacionService;

    @PostMapping
    @Secured("ADMIN")
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
    @Operation(
            summary = "Lista las habitaciones",
            description = "",
            tags = {"get"})
    public ResponseEntity<Page<DatosListaHabitacion>> obtenerDatos(@PageableDefault(size = 80, page = 0) Pageable pageable) {
        return ResponseEntity.ok(habitacionService.listHabitacion(pageable));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Lista las habitaciones por id",
            description = "",
            tags = {"get"})
    public ResponseEntity<DatosRespuestaHabitacion> getDataId(@PathVariable Long id) {
        return ResponseEntity.ok(habitacionService.listHabitacionId(id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Inhabilita las habitaciones",
            description = "",
            tags = {"delete"})
    public ResponseEntity<Object> EliminarHabitacion(@PathVariable Long id) {
        habitacionService.deshabilitarHabitacion(id);
        return ResponseEntity.noContent().build();
    }
}