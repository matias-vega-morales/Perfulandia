package com.example.API_Vendedor.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "metas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMeta;

    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private VendedorModelo vendedor;

    private String descripcion;
    private Double objetivoVenta; // Por ejemplo: 100000.0
    private LocalDate mes; // Solo se considera el mes y año (ej: 2025-06-01)
}
