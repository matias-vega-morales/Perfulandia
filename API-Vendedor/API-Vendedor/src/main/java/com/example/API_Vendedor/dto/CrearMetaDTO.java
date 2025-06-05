package com.example.API_Vendedor.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CrearMetaDTO {
    private Integer idVendedor;
    private String descripcion;
    private Double objetivoVenta;
    private LocalDate mes;
}
