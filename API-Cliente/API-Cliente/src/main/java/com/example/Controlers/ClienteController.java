package com.example.Controlers;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.API_Cliente.Modelo.ClienteModelo;
import com.example.API_Cliente.Modelo.PedidoModelo;
import com.example.Services.ClienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteModelo> crearCliente(@RequestBody ClienteModelo cliente) {
        ClienteModelo nuevo = clienteService.crearCliente(cliente);
        return ResponseEntity.status(201).body(nuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteModelo> obtenerCliente(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteService.obtenerClientePorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteModelo> actualizarCliente(@PathVariable Integer id, @RequestBody ClienteModelo cliente) {
        ClienteModelo actualizado = clienteService.actualizarCliente(id, cliente);
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping("/preferencias")
    public ResponseEntity<List<PedidoModelo>> historialCompras(@RequestParam Integer idCliente) {
        List<PedidoModelo> historial = clienteService.obtenerHistorialDeCompras(idCliente);
        return ResponseEntity.ok(historial);
    }
}
