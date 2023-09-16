package com.alura.hotel.model.reserva.validaciones;

import com.alura.hotel.model.reserva.DatosRegistroReserva;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class TiempoDeReserva implements ValidacionReserva{
    @Override
    public void validar(DatosRegistroReserva datos) {
        var ahora = LocalDateTime.now();
        var fechaReserva = datos.checkOut();

        var diferenciaFecha = Duration.between(ahora, fechaReserva).toDays();

        if (diferenciaFecha < 1) {
            throw new ValidationException("Las reservas de las habitaciones deben ser de al menos un día");
        }
    }
}
