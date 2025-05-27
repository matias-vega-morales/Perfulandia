package com.example.API_Inventario.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long inventarioId;
    private Integer cantidad;
    private String tipo; // "ENTRADA" o "SALIDA"
    private String descripcion;
    private LocalDateTime fecha = LocalDateTime.now();
}
