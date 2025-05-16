package com.example.API_Autenticacion_y_gestion_de_usuario.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")       // Nombre de la tabla en la base de datos
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id                         // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // Generación automática del ID
    private Long id;            // ID del usuario Long es un tipo de dato que puede almacenar números enteros grandes

    @Column(unique = true)     // Indica que este campo debe ser único en la base de datos
    private String username;     // Nombre de usuario
    private String password;     // Contraseña del usuario
    private String email;        // Correo electrónico del usuario
    private int estado;         // Estado del usuario (habilitado o no)

    @ManyToOne
    @JoinColumn(name = "rol_id")    // Indica que el campo rol_id es una clave foránea que hace referencia a la tabla roles
    private Rol rol;               // Rol del usuario (por ejemplo, admin, user, etc.)



}
