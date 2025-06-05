package com.example.dto;

import java.time.LocalDateTime;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Integer idPedido;
    private Integer idCliente;
    private LocalDateTime fechaPedido;
    private String estado;
    private Double montoTotal;
}

