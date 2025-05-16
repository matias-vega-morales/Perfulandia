package com.example.API_Autenticacion_y_gestion_de_usuario.security;

import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;


@Configuration      // Clase de configuración de seguridad
@EnableWebSecurity  // Habilita la seguridad web en la aplicación
@RequiredArgsConstructor // Genera un constructor para la clase
public class SecurityConfig {
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable()) // Desactiva la protección CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login", "/api/auth/registro").permitAll() // Permite acceso sin autenticación a estas rutas
                        .requestMatchers("/api/auth/usuarios/**").hasRole("ADMIN") // Permite acceso solo a usuarios con rol ADMIN
                        .requestMatchers("/api/auth/usuarios").hasAnyRole("ADMIN", "USER") // Permite acceso a usuarios con rol ADMIN o USER
                        .requestMatchers("/api/auth/usuarios/{id}").hasAnyRole("ADMIN", "USER") // Permite acceso a usuarios con rol ADMIN o USER
                        .anyRequest().authenticated() // Requiere autenticación para cualquier otra ruta
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configura la gestión de sesiones para no mantener estado
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Añade el filtro JWT antes del filtro de autenticación por nombre de usuario y contraseña
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    

}
}
