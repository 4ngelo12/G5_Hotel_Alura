package com.alura.hotel.domain.prueba;

public record PruebaRespuesta(Long id, String nombre, String apel, Integer numero) {
    public PruebaRespuesta(Prueba Prueba) {
        this(Prueba.getId(), Prueba.getNombre(), Prueba.getApell(), Prueba.getNumero());
    }
}
