package com.alura.hotel.model.habitacion;

import java.math.BigDecimal;

public record DatosRespuestaHabitacion(Long id, Integer numero, Boolean disponible, String descripcion, Integer numCamas,
                                       BigDecimal precio, Long idTipoHabitacion) {
    public DatosRespuestaHabitacion(Habitacion habitacion) {
        this(habitacion.getId(), habitacion.getNumero(), habitacion.getDisponible(), habitacion.getDescripcion(),
                habitacion.getNumCamas(), habitacion.getPrecio(), habitacion.getTipoHabitacion().getId());
    }
}
