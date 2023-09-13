package com.alura.hotel.model.tipoHabitacion;

import jakarta.persistence.*;

@Table(name = "tipo_habitacion")
@Entity(name = "TipoHabitacion")
public class TipoHabitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
}
