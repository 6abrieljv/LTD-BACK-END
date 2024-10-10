package com.academia.ltd.mensalidades;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensalidadeRepository extends JpaRepository<Mensalidade, Long> {
    //Pesquisa da messalidade por cpf
    List<Mensalidade> findByCliente_Cpf(String cpf);
}