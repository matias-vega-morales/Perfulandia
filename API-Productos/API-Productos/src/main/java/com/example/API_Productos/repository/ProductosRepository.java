package com.example.API_Productos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.API_Productos.models.Productos;

public interface ProductosRepository extends JpaRepository<Productos, Long> {
    List<Productos> findByCategoriaIgnoreCase(String categoria);
}