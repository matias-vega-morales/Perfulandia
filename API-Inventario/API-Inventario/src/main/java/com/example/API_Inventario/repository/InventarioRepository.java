package com.example.API_Inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.API_Inventario.models.Inventario;
import java.util.List;


@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    List<Inventario> findByProductoId(Long productoId); // Método para buscar inventario por ID de producto

    

    
} 
