package com.example.API_Vendedor.repository;

import com.example.API_Vendedor.models.SucursalAsignada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalRepository extends JpaRepository<SucursalAsignada, Integer> {}
