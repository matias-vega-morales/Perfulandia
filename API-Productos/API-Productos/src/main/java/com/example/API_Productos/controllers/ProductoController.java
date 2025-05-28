package com.example.API_Productos.controllers;

import com.example.API_Productos.models.Productos;
import com.example.API_Productos.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    private final ProductosRepository productosRepository;

    @Autowired      // Inyección de dependencias para el repositorio de productos
    public ProductoController(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    @GetMapping // Endpoint para obtener todos los productos
    public ResponseEntity<?> getAll() {
        List<Productos> productos = productosRepository.findAll();
        if (productos.isEmpty()) {
            return ResponseEntity.ok("No hay productos registrados.");
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")        // Endpoint para obtener un producto por su ID
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Productos> producto = productosRepository.findById(id);
        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.status(404).body("Producto no encontrado");
        }
    }

    
    @GetMapping("/categoria/{categoria}")     // Endpoint para obtener productos por categoría
    public ResponseEntity<?> getByCategoria(@PathVariable String categoria) {
        List<Productos> productos = productosRepository.findByCategoriaIgnoreCase(categoria);
        if (productos.isEmpty()) {
            return ResponseEntity.ok("No hay productos registrados en la categoría: " + categoria);
        }
        return ResponseEntity.ok(productos);
    }

    @PostMapping("/NuevoProducto")        // Endpoint para crear un nuevo producto
    public ResponseEntity<?> create(@RequestBody Productos producto) {
        Productos nuevo = productosRepository.save(producto);
        return ResponseEntity.status(201).body("Producto creado exitosamente: " + nuevo.getId_producto());
    }

    @PutMapping("/{id}") // Endpoint para actualizar un producto por su ID
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Productos productoActualizado) {
        Optional<Productos> productoOptional = productosRepository.findById(id);
        if (productoOptional.isPresent()) {
            Productos producto = productoOptional.get();
            producto.setNombre(productoActualizado.getNombre());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setPrecio(productoActualizado.getPrecio());
            producto.setCategoria(productoActualizado.getCategoria());
            producto.setMarca(productoActualizado.getMarca());
            producto.setMml(productoActualizado.getMml());
            productosRepository.save(producto);
            return ResponseEntity.ok("Producto actualizado exitosamente");
        } else {
            return ResponseEntity.status(404).body("Producto no encontrado");
        }
    }

    @DeleteMapping("/{id}") // Endpoint para eliminar un producto por su ID
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (productosRepository.existsById(id)) {
            productosRepository.deleteById(id);
            return ResponseEntity.ok("Producto borrado exitosamente");
        }
        return ResponseEntity.status(404).body("Producto no encontrado");
    }

}
