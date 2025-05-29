package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CrearUsuario {
    private String nombreUsuario;
    private String email;
    @JsonProperty("contrasenia")
    private String contrasenia;
    private String rol;
    private String estado;
    private String nombreCompleto;
    private String rut;
    private String direccion;
    private String telefono;
    private String areaVentas;
}

