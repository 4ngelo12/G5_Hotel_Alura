package com.alura.hotel.model.reserva.validaciones;

import com.alura.hotel.model.repository.ReservaRepository;
import com.alura.hotel.model.reserva.DatosCancelarReserva;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarHorarioAntecedencia implements ValidadorCancelamientoReserva{
    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public void validar(DatosCancelarReserva datos) {
        var reserva = reservaRepository.getReferenceById(datos.id());
        var ahora = LocalDateTime.now();
        var diferenciaEntreHoras = Duration.between(ahora, reserva.getCheckIn()).toHours();

        if (diferenciaEntreHoras < 24) {
            throw new ValidationException("Las reservas solo pueden ser canceladas con antecedencia " +
                    "mínima de 24 horas");
        }
    }
}
