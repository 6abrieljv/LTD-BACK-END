package com.academia.ltd.clientes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    
    // Método para verificar se já existe um cliente com o e-mail informado
    boolean existsByEmail(String email);
}
