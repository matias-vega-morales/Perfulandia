package com.example.API_Autenticacion_y_gestion_de_usuario.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")       // Nombre de la tabla en la base de datos
public class Rol {

    @Id                        // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // Generación automática del ID
    private Long id;            // ID del rol Long es un tipo de dato que puede almacenar números enteros grandes

    @Column(unique = true)     // Indica que este campo debe ser único en la base de datos
    private String nombre;     // Nombre del rol (por ejemplo, admin, user, etc.)
}
