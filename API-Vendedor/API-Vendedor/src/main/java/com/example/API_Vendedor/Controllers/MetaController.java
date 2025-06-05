package com.example.API_Vendedor.Controllers;

import com.example.API_Vendedor.Service.MetaService;
import com.example.API_Vendedor.dto.CrearMetaDTO;
import com.example.API_Vendedor.dto.MetaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metas")
@RequiredArgsConstructor
public class MetaController {

    private final MetaService metaService;

    @PostMapping
    public ResponseEntity<MetaDTO> crearMeta(@RequestBody CrearMetaDTO dto) {
        return ResponseEntity.ok(metaService.crearMeta(dto));
    }

    @GetMapping("/vendedor/{idVendedor}")
    public ResponseEntity<List<MetaDTO>> obtenerMetas(@PathVariable Integer idVendedor) {
        return ResponseEntity.ok(metaService.obtenerMetasPorVendedor(idVendedor));
    }
}
