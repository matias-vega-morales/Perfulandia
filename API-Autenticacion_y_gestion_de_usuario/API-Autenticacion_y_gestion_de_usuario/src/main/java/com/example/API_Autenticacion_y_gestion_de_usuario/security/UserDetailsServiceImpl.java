package com.example.API_Autenticacion_y_gestion_de_usuario.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.API_Autenticacion_y_gestion_de_usuario.models.Usuario;
import com.example.API_Autenticacion_y_gestion_de_usuario.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository userRepository;

    @Autowired      // Inyección de dependencias
    public UserDetailsServiceImpl(UsuarioRepository userRepository) {  // Constructor inyectado con el repositorio de usuarios
        this.userRepository = userRepository;   // Inicialización del repositorio en el constructor
    }

    @Override   // Método que carga el usuario por su nombre de usuario
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // Busca el usuario por el nombre de usuario y lanza una excepción si no lo encuentra
        Usuario usuario = userRepository.findByUsername(username) // Busca el usuario en la base de datos
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));    // Lanza una excepción si no se encuentra el usuario
        return new UserDetailsImpl(usuario);    // Devuelve un objeto UserDetailsImpl que implementa la interfaz UserDetails
    }
}
