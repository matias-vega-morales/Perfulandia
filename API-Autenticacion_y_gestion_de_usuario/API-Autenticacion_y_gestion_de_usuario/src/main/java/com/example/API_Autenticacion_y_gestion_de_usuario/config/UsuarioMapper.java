package com.example.API_Autenticacion_y_gestion_de_usuario.config;

import org.springframework.stereotype.Component;

import com.example.API_Autenticacion_y_gestion_de_usuario.dto.UsuarioDTO;
import com.example.API_Autenticacion_y_gestion_de_usuario.models.Usuario;

@Component
public class UsuarioMapper {
    public UsuarioDTO usuarioToDto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombreUsuario(usuario.getNombreUsuario());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRol());
        dto.setEstado(usuario.getEstado());
        return dto;

}
}
