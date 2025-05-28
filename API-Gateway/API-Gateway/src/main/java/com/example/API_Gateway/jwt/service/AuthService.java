package com.example.API_Gateway.jwt.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.API_Gateway.jwt.dto.AuthResponse;
import com.example.API_Gateway.jwt.dto.LoginRequest;
import com.example.API_Gateway.jwt.model.Usuario;
import com.example.API_Gateway.jwt.repository.UsuarioRepository;
import com.example.API_Gateway.jwt.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthResponse login(LoginRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getNombreUsuario(), request.getContrasena()));

        Usuario usuario = usuarioRepository.findByNombreUsuario(request.getNombreUsuario())
                .orElseThrow();

        if (!"activo".equalsIgnoreCase(usuario.getEstado())) {
            throw new BadCredentialsException("Usuario inactivo");
        }

        String token = jwtUtil.generateToken(usuario.getNombreUsuario(), usuario.getRol());
        return new AuthResponse(token, usuario.getNombreUsuario(), usuario.getRol());
    }

}
