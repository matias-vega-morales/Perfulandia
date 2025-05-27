package com.example.API_Autenticacion_y_gestion_de_usuario.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username; // Nombre de usuario
    private String password; // Contraseña del usuario
}
