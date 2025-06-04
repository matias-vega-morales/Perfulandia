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

    private Long id_producto;
    private Integer stock_disponible; // Cantidad de stock disponible
    private String ubicacion_bodega; // Ubicación del producto en el inventario  
}
