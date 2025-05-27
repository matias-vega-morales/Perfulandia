package com.example.API_Gateway.jwt.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String nombreUsuario;
    private String contrasena;

}
