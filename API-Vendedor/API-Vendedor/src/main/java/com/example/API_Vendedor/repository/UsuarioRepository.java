package com.example.API_Vendedor.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.API_Vendedor.models.UsuarioModelo;

public interface UsuarioRepository extends JpaRepository<UsuarioModelo, Integer> {

}