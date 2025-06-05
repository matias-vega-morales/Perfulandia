package com.example.API_Vendedor.Service;

import com.example.API_Vendedor.models.SucursalAsignada;
import com.example.API_Vendedor.models.VendedorModelo;
import com.example.API_Vendedor.repository.SucursalRepository;
import com.example.API_Vendedor.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendedorService {

    private final VendedorRepository vendedorRepo;
    private final SucursalRepository sucursalRepo;

    public VendedorModelo crearVendedor(VendedorModelo vendedor) {
        if (vendedor.getSucursal() != null) {
            Integer idSucursal = vendedor.getSucursal().getIdSucursal();
            SucursalAsignada sucursal = sucursalRepo.findById(idSucursal)
                    .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
            vendedor.setSucursal(sucursal);
        }
        return vendedorRepo.save(vendedor);
    }

    public VendedorModelo obtenerPorId(Integer id) {
        return vendedorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));
    }

    public VendedorModelo actualizarVendedor(Integer id, VendedorModelo datos) {
        VendedorModelo vendedor = obtenerPorId(id);

        vendedor.setNombreCompleto(datos.getNombreCompleto());
        vendedor.setIdUsuario(datos.getIdUsuario());
        vendedor.setRut(datos.getRut());
        vendedor.setDireccion(datos.getDireccion());
        vendedor.setTelefono(datos.getTelefono());

        if (datos.getSucursal() != null) {
            Integer idSucursal = datos.getSucursal().getIdSucursal();
            SucursalAsignada sucursal = sucursalRepo.findById(idSucursal)
                    .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
            vendedor.setSucursal(sucursal);
        } else {
            vendedor.setSucursal(null);
        }

        return vendedorRepo.save(vendedor);
    }

    public List<VendedorModelo> obtenerPorSucursal(Integer idSucursal) {
        return vendedorRepo.findBySucursalIdSucursal(idSucursal);
    }
}
