package com.example.API_Vendedor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.API_Vendedor.models.VendedorModelo;

public interface VendedorRepository extends JpaRepository<VendedorModelo, Integer> {

}