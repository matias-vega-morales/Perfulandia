package com.example.API_Autenticacion_y_gestion_de_usuario.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    public String extractUsername(String token) {       // Método para extraer el nombre de usuario del token JWT
        return extractClaims(token).getSubject();    // Llama al método extractClaims para obtener los reclamos del token y devuelve el sujeto (nombre de usuario)
    }

    public boolean validateToken(String token, UserDetails userDetails) {   // Método para validar el token JWT
        String username = extractUsername(token);   // Extrae el nombre de usuario del token
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);    // Compara el nombre de usuario extraído con el nombre de usuario del objeto UserDetailsImpl y verifica si el token no ha expirado
       
    }

    public String generateToken(UserDetails userDetails) {  // Método para generar un token JWT
        return Jwts.builder()         // Crea un nuevo token
                .setSubject(userDetails.getUsername())  // Establece el sujeto del token (nombre de usuario)
                .setIssuedAt(new Date(System.currentTimeMillis()))  // Establece la fecha de emisión del token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // Establece la fecha de expiración del token (10 horas a partir de ahora)
                .signWith(SignatureAlgorithm.HS256, secret) // Firma el token con el algoritmo HS256 y la clave secreta
                .compact(); // Compacta el token para que esté listo para ser enviado
    }

    private boolean isTokenExpired(String token) {  // Método para verificar si el token ha expirado
        return extractClaims(token).getExpiration().before(new Date()); // Compara la fecha de expiración del token con la fecha actual
    }

    private Claims extractClaims(String token) {    // Método para extraer los reclamos del token JWT
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody(); // Analiza el token y devuelve los reclamos (información) contenidos en él
    }

}
