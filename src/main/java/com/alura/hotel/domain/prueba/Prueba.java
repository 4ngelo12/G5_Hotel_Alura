package com.alura.hotel.domain.prueba;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pruebas")
@Entity(name = "Prueba")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Prueba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apell;
    private Integer numero;

    public Prueba(PrubeaDTO prubeaDTO) {
        this.nombre = prubeaDTO.nombre();
        this.apell = prubeaDTO.apel();
        this.numero = prubeaDTO.numero();
    }
}
