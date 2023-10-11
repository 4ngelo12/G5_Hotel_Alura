package com.alura.hotel.model.repository;

import com.alura.hotel.model.reserva.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
    Page<Reserva> findByUsuarioIdAndMotivoCancelamientoIsNull(Pageable paginacion, Long UsuarioId);
}
