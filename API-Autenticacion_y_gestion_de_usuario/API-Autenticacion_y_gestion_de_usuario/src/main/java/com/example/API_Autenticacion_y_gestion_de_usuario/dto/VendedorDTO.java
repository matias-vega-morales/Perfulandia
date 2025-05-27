package com.example.API_Autenticacion_y_gestion_de_usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendedorDTO {
    private Integer idVendedor;
    private Integer idUsuario;
    private String nombreCompleto;
    private String rut;
    private String areaVentas;

}
