package com.example.Controlers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Services.PedidoService;
import com.example.dto.PedidoDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/clientes/{idCliente}/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    public List<PedidoDTO> getPedidosPorCliente(@PathVariable Integer idCliente) {
        return pedidoService.listarPedidosPorCliente(idCliente);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> crearPedido(@PathVariable Integer idCliente, @RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO creado = pedidoService.crearPedido(idCliente, pedidoDTO);
        return ResponseEntity.status(201).body(creado);
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<?> getPedidoPorId(@PathVariable Integer idPedido) {
        try {
            PedidoDTO pedido = pedidoService.buscarPedidoPorId(idPedido);
            return ResponseEntity.ok(pedido);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(Map.of("mensaje", ex.getMessage()));
        }
    }
}
