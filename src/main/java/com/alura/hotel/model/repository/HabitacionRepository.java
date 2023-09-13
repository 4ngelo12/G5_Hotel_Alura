package com.alura.hotel.model.repository;

import com.alura.hotel.model.habitacion.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
}
