package com.example.API_Autenticacion_y_gestion_de_usuario.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.API_Autenticacion_y_gestion_de_usuario.models.Rol;

public interface Rolrepository extends JpaRepository<Rol, Long> {           // Extiende de JpaRepository para proporcionar métodos CRUD básicos
    // Aquí puedes agregar métodos personalizados ejemplo, buscar un rol por su nombre
    
    Optional<Rol> findByNombre(String nombre);      // Método para buscar un rol por su nombre
  
} 
