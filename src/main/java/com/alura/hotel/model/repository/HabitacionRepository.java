package com.alura.hotel.model.repository;

import com.alura.hotel.model.habitacion.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    @Query("""
            select h.disponible
            from Habitacion h
            where h.id=:habitacionId
            """)
    Boolean findDisponibleById(Long habitacionId);
}
