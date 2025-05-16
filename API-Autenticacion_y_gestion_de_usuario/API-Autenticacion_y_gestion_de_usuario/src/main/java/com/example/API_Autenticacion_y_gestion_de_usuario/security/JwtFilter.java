package com.example.API_Autenticacion_y_gestion_de_usuario.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;      // Utilidad para manejar JWT
    private final UserDetailsServiceImpl userDetailsService;    // Servicio para cargar detalles del usuario

    public JwtFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {  // Constructor inyectado con JwtUtil y UserDetailsServiceImpl
        this.jwtUtil = jwtUtil;   // Inicialización de JwtUtil
        this.userDetailsService = userDetailsService;   // Inicialización de UserDetailsServiceImpl
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)  // Método que se ejecuta para filtrar la solicitud
            throws ServletException, IOException {  // Validación del encabezado de autorización JWT
        String authHeader = request.getHeader("Authorization"); // Obtiene el encabezado de autorización de la solicitud

        if (authHeader != null && authHeader.startsWith("Bearer ")) {   // Verifica si el encabezado no es nulo y comienza con "Bearer "
            String token = authHeader.substring(7); // Extrae el token del encabezado
            String username = jwtUtil.extractUsername(token);   // Extrae el nombre de usuario del token

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {   // Verifica si el nombre de usuario no es nulo y no hay autenticación previa en el contexto de seguridad
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);  // Carga los detalles del usuario desde la base de datos
                if (jwtUtil.validateToken(token, userDetails)) {    // Valida el token con los detalles del usuario
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());   // Crea un token de autenticación con los detalles del usuario
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));   // Establece los detalles de autenticación
                    SecurityContextHolder.getContext().setAuthentication(authToken);    // Establece el token de autenticación en el contexto de seguridad
                }
            }
        }

        filterChain.doFilter(request, response); // Continúa con la cadena de filtros
    }
}

