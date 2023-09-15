package com.alura.hotel.model.service;

import com.alura.hotel.infra.errores.ValidacionDeIntegridad;
import com.alura.hotel.infra.security.TokenService;
import com.alura.hotel.model.formatoPago.TipoPago;
import com.alura.hotel.model.habitacion.Habitacion;
import com.alura.hotel.model.repository.HabitacionRepository;
import com.alura.hotel.model.repository.ReservaRepository;
import com.alura.hotel.model.repository.TipoPagoRepository;
import com.alura.hotel.model.repository.UsuarioRepository;
import com.alura.hotel.model.reserva.DatosRegistroReserva;
import com.alura.hotel.model.reserva.DatosRespuestaReserva;
import com.alura.hotel.model.reserva.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private TipoPagoRepository tipoPagoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private HabitacionRepository habitacionRepository;
    @Autowired
    private TokenService tokenService;

    public DatosRespuestaReserva registrarReserva(String token, DatosRegistroReserva datos) {
        var jwtToken = token.replace("Bearer ", "");
        var nombreUsuario = tokenService.getSubject(jwtToken);
        var usuario = usuarioRepository.getUserData(nombreUsuario);

        if (usuario.getRole().getNombre().equals("EMPLEADO")) {
            throw new ValidacionDeIntegridad("No está autorizado para realizar esta acción");
        }

        if (habitacionRepository.findById(datos.habitacionId()).isEmpty()) {
            throw new ValidacionDeIntegridad("La habitación que busca no existe");
        }

        if (tipoPagoRepository.findById(datos.tipoPagoId()).isEmpty()) {
            throw new ValidacionDeIntegridad("El tipo de pago que busca no se encuentra disponible");
        }

        Habitacion habitacion = habitacionRepository.findById(datos.habitacionId()).get();
        TipoPago tipoPago = tipoPagoRepository.findById(datos.tipoPagoId()).get();
        var reserva = new Reserva(datos, usuario, habitacion, tipoPago);
        reservaRepository.save(reserva);

        return new DatosRespuestaReserva(reserva);
    }

    public void Prueba(String token) {
        var jwtToken = token.replace("Bearer ", "");
        var nombreUsuario = tokenService.getSubject(jwtToken);
        var usuario = usuarioRepository.getUserData(nombreUsuario);

        System.out.println(usuario.getId());
    }
}
