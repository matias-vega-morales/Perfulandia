package com.example.API_Vendedor.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MetaDTO {
    private Integer idMeta;
    private Integer idVendedor;
    private String descripcion;
    private Double objetivoVenta;
    private LocalDate mes;
}
