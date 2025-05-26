package com.example.API_Inventario.service;

import com.example.API_Inventario.models.Inventario;
import com.example.API_Inventario.models.MovimientoInventario;
import com.example.API_Inventario.repository.InventarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {
    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public List<Inventario> findAll() {
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> findById(Long id) {
        return inventarioRepository.findById(id);
    }

    public List<Inventario> findByProductoId(Long productoId) {
        return inventarioRepository.findByProductoId(productoId);
    }

    public Inventario save(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public void deleteById(Long id) {
        inventarioRepository.deleteById(id);
    }

    public void registrarMovimiento(MovimientoInventario movimiento) {      // Método para registrar un movimiento de inventario
        // Buscar el inventario relacionado
        Optional<Inventario> inventarioOpt = inventarioRepository.findById(movimiento.getInventarioId());
        if (inventarioOpt.isPresent()) {
            Inventario inventario = inventarioOpt.get();
            // Ajustar la cantidad según el tipo de movimiento
            if ("ENTRADA".equalsIgnoreCase(movimiento.getTipo())) {     // Si es una entrada, sumamos la cantidad
                inventario.setCantidad(inventario.getCantidad() + movimiento.getCantidad());
            } else if ("SALIDA".equalsIgnoreCase(movimiento.getTipo())) {       // Si es una salida, restamos la cantidad
                inventario.setCantidad(inventario.getCantidad() - movimiento.getCantidad());
            }
            inventarioRepository.save(inventario);  // Guardar el inventario actualizado
            // Aquí deberías guardar el movimiento en su propio repositorio si tienes uno
            // movimientoInventarioRepository.save(movimiento);
        }
        // Si el inventario no existe, podrías lanzar una excepción o manejar el error según tu lógica
    }
    
}
