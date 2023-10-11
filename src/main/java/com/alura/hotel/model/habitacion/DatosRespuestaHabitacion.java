package com.alura.hotel.model.habitacion;

public record DatosRespuestaHabitacion(Long id, Integer numero, Boolean disponible, String descripcion,
                                       Integer numCamas,
                                       Double precio, String tipoHabitacion) {
    public DatosRespuestaHabitacion(Habitacion habitacion) {
        this(habitacion.getId(), habitacion.getNumero(), habitacion.getDisponible(), habitacion.getDescripcion(),
                habitacion.getNumCamas(), habitacion.getPrecio(), habitacion.getTipoHabitacion().getTipo());
    }
}
