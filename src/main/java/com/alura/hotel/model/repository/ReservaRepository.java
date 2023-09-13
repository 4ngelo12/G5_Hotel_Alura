package com.alura.hotel.model.repository;

import com.alura.hotel.model.reserva.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
}
