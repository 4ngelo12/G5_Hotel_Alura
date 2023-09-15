package com.alura.hotel.model.service;

import com.alura.hotel.infra.errores.ValidacionDeIntegridad;
import com.alura.hotel.model.habitacion.DatosRegistroHabitacion;
import com.alura.hotel.model.habitacion.DatosRespuestaHabitacion;
import com.alura.hotel.model.habitacion.Habitacion;
import com.alura.hotel.model.repository.HabitacionRepository;
import com.alura.hotel.model.repository.TipoHabitacionRepository;
import com.alura.hotel.model.tipoHabitacion.TipoHabitacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabitacionService {
    @Autowired
    private HabitacionRepository habitacionRepository;
    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    public DatosRespuestaHabitacion registrarHabitacion(DatosRegistroHabitacion datos) {
        if (tipoHabitacionRepository.findById(datos.idTipoHabitacion()).isEmpty()) {
            throw new ValidacionDeIntegridad("El tipo de habitación que busca no existe");
        }

        TipoHabitacion tipoHabitacion = tipoHabitacionRepository.findById(datos.idTipoHabitacion()).get();

        var habitacion = new Habitacion(datos, tipoHabitacion);
        habitacionRepository.save(habitacion);

        return new DatosRespuestaHabitacion(habitacion);
    }
}
