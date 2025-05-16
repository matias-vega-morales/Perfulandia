package com.example.API_Autenticacion_y_gestion_de_usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.API_Autenticacion_y_gestion_de_usuario.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, buscar un usuario por su nombre de usuario
    Optional<Usuario> findByUsername(String username);

}
