package com.example.API_Autenticacion_y_gestion_de_usuario.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username; // Nombre de usuario
    private String password; // Contraseña del usuario
    private String email; // Correo electrónico del usuario
    private String nombre; // Nombre real del usuario
    private String apellido; // Apellido del usuario
    private int estado; // Estado del usuario (activo/inactivo)
    private int idRol; // ID del rol del usuario
}
