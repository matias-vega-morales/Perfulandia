package com.example.API_Inventario.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.API_Inventario.models.Inventario;
import com.example.API_Inventario.models.MovimientoInventario;
import com.example.API_Inventario.service.InventarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioController {

private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping
    public ResponseEntity<List<Inventario>> getAll() {
        return ResponseEntity.ok(inventarioService.findAll());
    }

    @GetMapping("/{id}")    // Consultar inventario por ID
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Inventario> inventario = inventarioService.findById(id);
        if (inventario.isPresent()) {
            return ResponseEntity.ok(inventario.get());
        } else {
            return ResponseEntity.status(404).body("Inventario no encontrado");
        }
    }

    // Consultar inventario por producto
    @GetMapping("/producto/{id}")       // Consultar inventario por ID de producto
    public ResponseEntity<?> getByProductoId(@PathVariable Long id) {
        List<Inventario> inventarios = inventarioService.findByProductoId(id);
        if (inventarios.isEmpty()) {
            return ResponseEntity.status(404).body("No hay inventario para el producto con id: " + id);
        }
        return ResponseEntity.ok(inventarios);
    }

    @PostMapping        // Crear nuevo inventario
    public ResponseEntity<Inventario> create(@RequestBody Inventario inventario) {
        return ResponseEntity.status(201).body(inventarioService.save(inventario));
    }

    // Ajuste de stock (PUT o PATCH)
    @PutMapping("/ajuste")      // Ajustar stock de inventario
    public ResponseEntity<?> ajustarStock(@RequestBody Inventario ajuste) {
        return inventarioService.findById(ajuste.getId_inventario())
                .map(inventario -> {
                    inventario.setCantidad(ajuste.getCantidad());
                    inventarioService.save(inventario);
                    return ResponseEntity.ok("Stock ajustado exitosamente");
                })
                .orElse(ResponseEntity.status(404).body("Inventario no encontrado"));
    }

    @PutMapping("/{id}")        // Actualizar inventario por ID
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Inventario inventarioActualizado) {
        return inventarioService.findById(id)
                .map(inventario -> {
                    inventario.setProductoId(inventarioActualizado.getProductoId());
                    inventario.setCantidad(inventarioActualizado.getCantidad());
                    inventarioService.save(inventario);
                    return ResponseEntity.ok("Inventario actualizado exitosamente");
                })
                .orElse(ResponseEntity.status(404).body("Inventario no encontrado"));
    }

    // Registrar movimiento de inventario
    @PostMapping("/movimiento")     // Registrar un movimiento de inventario
    public ResponseEntity<?> registrarMovimiento(@RequestBody MovimientoInventario movimiento) {
        // Aquí deberías agregar lógica para actualizar el inventario según el movimiento
        // y guardar el movimiento en la base de datos
        // Ejemplo simple:
        // Verificar si el inventario existe
        // y actualizar la cantidad según el tipo de movimiento (entrada/salida) 
        inventarioService.registrarMovimiento(movimiento);
        return ResponseEntity.status(201).body("Movimiento registrado exitosamente");

        


    }

    @DeleteMapping("/{id}")         // Eliminar inventario por ID
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (inventarioService.findById(id).isPresent()) {
            inventarioService.deleteById(id);
            return ResponseEntity.ok("Inventario eliminado exitosamente");
        }
        return ResponseEntity.status(404).body("Inventario no encontrado");
    }

    // Eliminar producto del inventario
    @DeleteMapping("/producto/{id}")        // Eliminar inventario por ID de producto
    public ResponseEntity<?> eliminarPorProducto(@PathVariable Long id) {
        List<Inventario> inventarios = inventarioService.findByProductoId(id);
        if (inventarios.isEmpty()) {
            return ResponseEntity.status(404).body("No hay inventario para el producto con id: " + id);
        }
        inventarios.forEach(inv -> inventarioService.deleteById(inv.getId_inventario()));
        return ResponseEntity.ok("Inventario del producto eliminado exitosamente");
    }
}
