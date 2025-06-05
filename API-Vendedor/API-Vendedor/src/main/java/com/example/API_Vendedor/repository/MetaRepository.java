package com.example.API_Vendedor.repository;

import com.example.API_Vendedor.models.MetaModelo;
import com.example.API_Vendedor.models.VendedorModelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetaRepository extends JpaRepository<MetaModelo, Integer> {
    List<MetaModelo> findByVendedor(VendedorModelo vendedor);
}
