package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.API_Cliente.Modelo.UsuarioModelo;

public interface UsuarioRepository extends JpaRepository<UsuarioModelo, Integer> {

}
