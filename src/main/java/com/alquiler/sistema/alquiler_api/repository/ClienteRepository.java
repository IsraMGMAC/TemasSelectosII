package com.alquiler.sistema.alquiler_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alquiler.sistema.alquiler_api.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByEmail(String email);
}
