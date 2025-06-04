package com.example.API_Vendedor.Service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.API_Vendedor.config.UsuarioMapper;
import com.example.API_Vendedor.dto.CrearUsuario;
import com.example.API_Vendedor.dto.UsuarioDTO;
import com.example.API_Vendedor.models.UsuarioModelo;
import com.example.API_Vendedor.models.VendedorModelo;
import com.example.API_Vendedor.repository.UsuarioRepository;
import com.example.API_Vendedor.repository.VendedorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepo;
    private final VendedorRepository vendedorRepo;
    private final PasswordEncoder passwordEncoder; 
    private final UsuarioMapper mapper;

    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepo.findAll().stream()
            .map(u -> new UsuarioDTO(u.getIdUsuario(), u.getNombreUsuario(), u.getEmail(), u.getRol(), u.getEstado()))
            .toList();
    }

    public UsuarioModelo crearUsuario(CrearUsuario req) {
        UsuarioModelo u = new UsuarioModelo();
        u.setNombreUsuario(req.getNombreUsuario());
        u.setEmail(req.getEmail());

        System.out.println("DEBUG >> contrasena: " + req.getContrasenia());
        System.out.println(req);

        // ✅ Validar que la contraseña no sea nula ni vacía
        String rawPassword = req.getContrasenia(); // ✅ correcto, coincide con el DTO
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException("La clave es requerida");
        }
        u.setContrasena(passwordEncoder.encode(rawPassword));

        u.setRol(req.getRol());
        u.setEstado(req.getEstado());

        UsuarioModelo nuevoUsuario = usuarioRepo.save(u);

        switch (req.getRol().toLowerCase()) {
            case "vendedor" -> {
                VendedorModelo v = new VendedorModelo();
                v.setIdVendedor(null);
                v.setUsuario(nuevoUsuario);
                v.setNombreCompleto(req.getNombreCompleto());
                v.setRut(req.getRut());
                v.setAreaVentas(req.getAreaVentas());
                vendedorRepo.save(v);
            }
            case "admin" -> {
                // No se necesita nada extra, solo se guarda como usuario
            }
            default -> throw new IllegalArgumentException("Rol no soportado: " + req.getRol());
        }

        return nuevoUsuario;
    }

    public UsuarioDTO buscarUsuarioPorId(Integer id) {
        UsuarioModelo usuario = usuarioRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        // Convertir Usuario a UsuarioDTO
        return mapper.usuarioToDto(usuario);
    }

    public UsuarioDTO actualizarUsuario(Integer id, UsuarioDTO dto) {
        UsuarioModelo usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setEmail(dto.getEmail());
        usuario.setEstado(dto.getEstado());
        usuario.setRol(dto.getRol());
        // agrega más campos si es necesario

        UsuarioModelo guardado = usuarioRepo.save(usuario);
        return mapper.usuarioToDto(guardado);
    }



    public void eliminarUsuario(Integer id) {
        usuarioRepo.deleteById(id);
    }
}
