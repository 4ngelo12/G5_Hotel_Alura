package com.alura.hotel.model.habitacion;

import com.alura.hotel.model.tipoHabitacion.TipoHabitacion;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Table(name = "habitaciones")
@Entity(name = "Habitacion")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;
    private Boolean estado;
    private String descripcion;
    private String numCamas;
    private BigDecimal precio;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoHabitacion_id")
    private TipoHabitacion tipoHabitacion;
}
