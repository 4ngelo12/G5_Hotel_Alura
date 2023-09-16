package com.alura.hotel.model.habitacion;

public record DatosListaHabitacion(Integer numero, Boolean disponible, String descripcion, Integer numCamas,
                                   Double precio, Long tipoHabitacionId) {
    public DatosListaHabitacion(Habitacion habitacion) {
        this(habitacion.getNumero(), habitacion.getDisponible(), habitacion.getDescripcion(),
                habitacion.getNumCamas(), habitacion.getPrecio(), habitacion.getTipoHabitacion().getId());
    }
}
