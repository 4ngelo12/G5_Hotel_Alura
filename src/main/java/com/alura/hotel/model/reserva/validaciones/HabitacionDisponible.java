package com.alura.hotel.model.reserva.validaciones;

import com.alura.hotel.model.repository.HabitacionRepository;
import com.alura.hotel.model.reserva.DatosRegistroReserva;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HabitacionDisponible implements ValidacionReserva{
    @Autowired
    private HabitacionRepository habitacionRepository;

    @Override
    public void validar(DatosRegistroReserva datos) {
        if (datos.habitacionId() == null) {
            return;
        }

        var habitacionDisponible = habitacionRepository.findDisponibleById(datos.habitacionId());

        if (!habitacionDisponible) {
            throw new ValidationException("La habtiación seleccionada no se encuentra disponible");
        }
    }
}
