package com.example.API_Vendedor.config;

import org.springframework.stereotype.Component;

import com.example.API_Vendedor.dto.UsuarioDTO;
import com.example.API_Vendedor.models.UsuarioModelo;

@Component
public class UsuarioMapper {
    public UsuarioDTO usuarioToDto(UsuarioModelo usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombreUsuario(usuario.getNombreUsuario());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRol());
        dto.setEstado(usuario.getEstado());
        return dto;
    }
}


