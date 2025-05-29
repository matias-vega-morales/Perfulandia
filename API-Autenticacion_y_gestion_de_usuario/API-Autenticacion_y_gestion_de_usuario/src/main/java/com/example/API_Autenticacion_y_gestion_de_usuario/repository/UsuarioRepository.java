package com.example.API_Autenticacion_y_gestion_de_usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.API_Autenticacion_y_gestion_de_usuario.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
   

}
