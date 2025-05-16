package com.example.API_Autenticacion_y_gestion_de_usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor

public class AuthResponse {
    private String token; // Token de autenticación
    private String username; // Nombre de usuario

    public AuthResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

}
