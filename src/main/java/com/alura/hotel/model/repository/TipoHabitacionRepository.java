package com.alura.hotel.model.repository;

import com.alura.hotel.model.tipoHabitacion.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoHabitacionRepository extends JpaRepository<TipoHabitacion, Long> {
}
