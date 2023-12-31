package com.alura.hotel.model.tipoHabitacion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tipo_habitacion")
@Entity(name = "TipoHabitacion")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TipoHabitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false)
    private String tipo;
}
