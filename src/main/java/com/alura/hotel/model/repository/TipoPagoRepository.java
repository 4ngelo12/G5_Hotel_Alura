package com.alura.hotel.model.repository;

import com.alura.hotel.model.formatoPago.TipoPago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoPagoRepository extends JpaRepository<TipoPago, Long> {
}
