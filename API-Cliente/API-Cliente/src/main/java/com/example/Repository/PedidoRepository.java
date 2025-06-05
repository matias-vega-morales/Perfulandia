package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.API_Cliente.Modelo.PedidoModelo;

public interface PedidoRepository extends JpaRepository<PedidoModelo, Integer> {
    List<PedidoModelo> findByClienteIdCliente(Integer idCliente);
}

