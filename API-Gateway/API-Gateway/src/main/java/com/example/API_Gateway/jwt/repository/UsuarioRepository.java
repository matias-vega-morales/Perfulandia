package com.example.API_Gateway.jwt.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.API_Gateway.jwt.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByNombreUsuario(String nombreUsuario); //Usa el nombre exacto del atributo


}

    



