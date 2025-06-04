package com.example.API_Productos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name="productos") // Nombre de la tabla en la base de datos
@Data                   // Genera automáticamente los métodos getters y setters
@NoArgsConstructor  // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos
public class Productos {
    @Id     // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;        // ID del producto

    private String nombre;      // Nombre del producto
    private String descripcion;     // Descripción del producto
    private BigDecimal precio;  // Precio del producto bigdecimal es un tipo de dato que puede almacenar números decimales grandes
    private String categoria;   // Categoria del producto
    private String marca;   // Marca del producto
    private String mml;
    private String activo;
}
