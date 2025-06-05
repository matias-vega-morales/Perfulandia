package com.example.API_Vendedor.Service;

import com.example.API_Vendedor.dto.CrearMetaDTO;
import com.example.API_Vendedor.dto.MetaDTO;
import com.example.API_Vendedor.models.MetaModelo;
import com.example.API_Vendedor.models.VendedorModelo;
import com.example.API_Vendedor.repository.MetaRepository;
import com.example.API_Vendedor.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MetaService {

    private final MetaRepository metaRepo;
    private final VendedorRepository vendedorRepo;

    public MetaDTO crearMeta(CrearMetaDTO dto) {
        VendedorModelo vendedor = vendedorRepo.findById(dto.getIdVendedor())
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

        MetaModelo meta = new MetaModelo();
        meta.setVendedor(vendedor);
        meta.setDescripcion(dto.getDescripcion());
        meta.setObjetivoVenta(dto.getObjetivoVenta());
        meta.setMes(dto.getMes());

        MetaModelo guardada = metaRepo.save(meta);

        return mapToDTO(guardada);
    }

    public List<MetaDTO> obtenerMetasPorVendedor(Integer idVendedor) {
        VendedorModelo vendedor = vendedorRepo.findById(idVendedor)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

        return metaRepo.findByVendedor(vendedor).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private MetaDTO mapToDTO(MetaModelo meta) {
        MetaDTO dto = new MetaDTO();
        dto.setIdMeta(meta.getIdMeta());
        dto.setIdVendedor(meta.getVendedor().getId_Vendedor());
        dto.setDescripcion(meta.getDescripcion());
        dto.setObjetivoVenta(meta.getObjetivoVenta());
        dto.setMes(meta.getMes());
        return dto;
    }
}
