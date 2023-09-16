package com.alura.hotel.model.habitacion;

import com.alura.hotel.model.tipoHabitacion.TipoHabitacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "habitaciones")
@Entity(name = "Habitacion")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;
    @Column(length = 5, nullable = false, columnDefinition = "tinyint")
    private Boolean disponible;
    @Column(length = 250, nullable = false)
    private String descripcion;
    private Integer numCamas;
    @Column(columnDefinition = "decimal", nullable = false)
    private Double precio;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoHabitacion_id")
    private TipoHabitacion tipoHabitacion;

    public Habitacion(DatosRegistroHabitacion datos, TipoHabitacion tipoHabitacion) {
        this.numero = datos.numero();
        this.disponible = true;
        this.descripcion = datos.descripcion();
        this.numCamas = datos.numCamas();
        this.precio = datos.precio();
        this.tipoHabitacion = tipoHabitacion;
    }

    public void OcuparHabitacion() {
        this.disponible = false;
    }
}
