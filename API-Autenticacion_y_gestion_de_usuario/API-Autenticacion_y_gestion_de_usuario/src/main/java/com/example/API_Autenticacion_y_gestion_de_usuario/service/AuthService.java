package com.example.API_Autenticacion_y_gestion_de_usuario.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.API_Autenticacion_y_gestion_de_usuario.dto.AuthResponse;
import com.example.API_Autenticacion_y_gestion_de_usuario.dto.LoginRequest;
import com.example.API_Autenticacion_y_gestion_de_usuario.models.Usuario;
import com.example.API_Autenticacion_y_gestion_de_usuario.repository.UsuarioRepository;
import com.example.API_Autenticacion_y_gestion_de_usuario.security.JwtUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;      // Repositorio para acceder a los datos de usuario
    private final PasswordEncoder passwordEncoder;      // Codificador de contraseñas para encriptar y verificar contraseñas
    private final JwtUtil jwtUtil;      // Utilidad para generar y validar tokens JWT
    private final AuthenticationManager authManager; // Cambiado aquí

    
    public AuthResponse login(LoginRequest request) { // Método para iniciar sesión
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String token = jwtUtil.generateToken((UserDetails) usuario); // Corrige el uso de JwtUtil
        return new AuthResponse(token, usuario.getUsername()); // Retorna el token y el nombre de usuario
    }



    /**
     * Registra un nuevo usuario en la base de datos con la contraseña cifrada.
     */
    public Usuario registrar(Usuario nuevoUsuario) {
        // Encripta la contraseña antes de guardar
        nuevoUsuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
        // Guarda el usuario en la base de datos
        return usuarioRepository.save(nuevoUsuario);
    }

    /**
     * Retorna un usuario por ID.
     */
    public Usuario getUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    /**
     * Elimina un usuario por ID.
     */
    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}



