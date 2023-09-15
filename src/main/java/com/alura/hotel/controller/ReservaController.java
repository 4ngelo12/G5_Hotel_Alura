package com.alura.hotel.controller;

import com.alura.hotel.infra.errores.ValidacionDeIntegridad;
import com.alura.hotel.model.reserva.DatosRegistroReserva;
import com.alura.hotel.model.reserva.DatosRespuestaReserva;
import com.alura.hotel.model.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserva")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Reservas", description = "Permite realizar las operaciones para las reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @PostMapping
    @Operation(
            summary = "Registra las reservas en la base de datos",
            description = "",
            tags = {"post"})
    public ResponseEntity<DatosRespuestaReserva> registrarReserva(@RequestHeader("Authorization") String token,
                                                                  @RequestBody @Valid DatosRegistroReserva datos)
            throws ValidacionDeIntegridad {
        var response = reservaService.registrarReserva(token, datos);

        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<String> obtenerDatos(@RequestHeader("Authorization") String token) {
        //Obtener el Token del Header
        return ResponseEntity.ok(token);
    }
}
