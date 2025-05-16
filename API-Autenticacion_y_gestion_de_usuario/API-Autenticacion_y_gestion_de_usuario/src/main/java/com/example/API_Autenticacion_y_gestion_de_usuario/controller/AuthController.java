package com.example.API_Autenticacion_y_gestion_de_usuario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.API_Autenticacion_y_gestion_de_usuario.dto.AuthResponse;
import com.example.API_Autenticacion_y_gestion_de_usuario.dto.LoginRequest;
import com.example.API_Autenticacion_y_gestion_de_usuario.models.Usuario;
import com.example.API_Autenticacion_y_gestion_de_usuario.service.AuthService;

@RestController     
@RequestMapping("/api/auth")  // Endpoint base para autenticación
public class AuthController {  
    private final AuthService authService;  // Inyección de dependencias para el servicio de autenticación

    public AuthController(AuthService authService) {    // Constructor inyectado con el servicio de autenticación
        this.authService = authService;     // Inicializa el servicio de autenticación
    }

    @PostMapping("/login")      // Endpoint para iniciar sesión
public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {  // Validación del encabezado de autorización JWT
    String token = authService.login(request.getUsername(), request.getPassword()); // Genera el token JWT y retorna el encabezado de respuesta con el token JWT y el nombre de usuario
    return ResponseEntity.ok(new AuthResponse(token, request.getUsername()));   // Retorna el encabezado de respuesta con el token JWT y el nombre de usuario
}



    @PostMapping("/registro")   // Endpoint para registrar un nuevo usuario
    public ResponseEntity<Usuario> registro(@RequestBody Usuario usuario) { // Recibe el objeto Usuario en el cuerpo de la solicitud
        Usuario nuevo = authService.registrar(usuario);     // Llama al servicio de autenticación para registrar el nuevo usuario
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);       // Retorna el nuevo usuario registrado con el código de estado 201 (CREATED)
    }

    @GetMapping("/usuarios/{id}")       // Endpoint para obtener un usuario por su ID
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {      // Recibe el ID del usuario como variable de ruta
        return ResponseEntity.ok(authService.getUsuario(id));       // Llama al servicio de autenticación para obtener el usuario por su ID y retorna el usuario con el código de estado 200 (OK)
    }

    @DeleteMapping("/usuarios/{id}")        // Endpoint para eliminar un usuario por su ID
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {        // Recibe el ID del usuario como variable de ruta
        authService.eliminarUsuario(id);        // Llama al servicio de autenticación para eliminar el usuario por su ID
        return ResponseEntity.noContent().build();      // Retorna una respuesta sin contenido con el código de estado 204 (NO CONTENT)
    }
}
