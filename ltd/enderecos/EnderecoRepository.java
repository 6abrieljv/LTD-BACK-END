package com.academia.ltd.enderecos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    // Pesquisa do endereco por cpf
    List<Endereco> findByClienteCpf(String cpf);


}
