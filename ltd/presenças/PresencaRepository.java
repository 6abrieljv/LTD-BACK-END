package com.academia.ltd.presenças;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresencaRepository extends JpaRepository<Presenca, Long> {

    // Método para buscar presenças por CPF do cliente
    List<Presenca> findByClienteCpf(String cpf);

    // Método para verificar se existe presença por CPF do cliente
    boolean existsByClienteCpf(String cpf);

    // Método para deletar presenças por CPF do cliente
    void deleteByClienteCpf(String cpf);
}

