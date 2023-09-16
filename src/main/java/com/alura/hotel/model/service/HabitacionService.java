package com.alura.hotel.model.service;

import com.alura.hotel.infra.errores.ValidacionDeIntegridad;
import com.alura.hotel.model.habitacion.DatosListaHabitacion;
import com.alura.hotel.model.habitacion.DatosRegistroHabitacion;
import com.alura.hotel.model.habitacion.DatosRespuestaHabitacion;
import com.alura.hotel.model.habitacion.Habitacion;
import com.alura.hotel.model.repository.HabitacionRepository;
import com.alura.hotel.model.repository.TipoHabitacionRepository;
import com.alura.hotel.model.tipoHabitacion.TipoHabitacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    public Page<DatosListaHabitacion> listHabitacion(Pageable paginacion) {
        return habitacionRepository.findByDisponibleTrue(paginacion).map(DatosListaHabitacion::new);
    }

    public void deshabilitarHabitacion(Long id) {
        Habitacion habitacion = habitacionRepository.getReferenceById(id);
        habitacion.OcuparHabitacion();
    }
}
