package com.example.API_Inventario.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_inventario;

    @Column(name = "producto_id", nullable = false)
    private Long productoId;

    @ManyToOne  // Relación con la entidad Almacen
    @JoinColumn(name = "almacen_id", referencedColumnName = "ubicacion", nullable = false) // Indica que el campo almacen_id es una clave foránea que hace referencia a la tabla almacenes
    private Almacen almacen; // Relación con la entidad Almacen

    private Integer cantidad;   
}
