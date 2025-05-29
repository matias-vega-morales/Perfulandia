package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.API_Cliente.Modelo.ClienteModelo;

public interface ClienteRepository extends JpaRepository<ClienteModelo, Integer> {

}
