package com.alura.hotel.model.formatoPago;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "TipoPago")
@Table(name = "tipo_pago")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TipoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10, nullable = false)
    private String nombre;
}
