package com.example.API_Vendedor.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "vendedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendedorModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vendedor") // mapea con la columna MySQL
    private Integer idVendedor;

    @Column(name = "id_usuario")
    private String idUsuario;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    private String rut;
    private String direccion;
    private int telefono;

    @ManyToOne
    @JoinColumn(name = "id_sucursal") // FK hacia sucursal
    private SucursalAsignada sucursal;
}
