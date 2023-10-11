package com.alura.hotel.controller;

import com.alura.hotel.infra.errores.ValidacionDeIntegridad;
import com.alura.hotel.model.reserva.DatosCancelarReserva;
import com.alura.hotel.model.reserva.DatosListaReserva;
import com.alura.hotel.model.reserva.DatosRegistroReserva;
import com.alura.hotel.model.reserva.DatosRespuestaReserva;
import com.alura.hotel.model.service.HabitacionService;
import com.alura.hotel.model.service.ReservaService;
import com.alura.hotel.model.service.UsuarioService;
import com.alura.hotel.model.usuario.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserva")
@CrossOrigin("*")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Reservas", description = "Permite realizar las operaciones para las reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private HabitacionService habitacionService;

    @PostMapping
    @Transactional
    @Operation(
            summary = "Registra las reservas en la base de datos",
            description = "",
            tags = {"post"})
    public ResponseEntity<DatosRespuestaReserva> registrarReserva(@RequestHeader("Authorization") String token,
                                                                  @RequestBody @Valid DatosRegistroReserva datos)
            throws ValidacionDeIntegridad {
        Usuario usuario = usuarioService.getUser(token);
        var response = reservaService.registrarReserva(usuario, datos);
        habitacionService.deshabilitarHabitacion(datos.habitacionId());

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(
            summary = "Lista las reservas",
            description = "",
            tags = {"get"})
    public ResponseEntity<Page<DatosListaReserva>> obtenerDatos(Pageable pageable, Long id) {
        return ResponseEntity.ok(reservaService.listarReservas(pageable, id));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelarReserva(@RequestBody @Valid DatosCancelarReserva datos) {
        reservaService.cancelarReserva(datos);
        return ResponseEntity.noContent().build();
    }
}
