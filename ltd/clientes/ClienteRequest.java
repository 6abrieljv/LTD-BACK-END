package com.academia.ltd.clientes;

import java.util.List;

import com.academia.ltd.enderecos.Endereco;
import com.academia.ltd.mensalidades.Mensalidade;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    @NotBlank
    private String cpf;

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String telefone;

    @NotBlank
    private String dataNascimento;

    @NotNull
    private List<Endereco> enderecos;
    
    @NotNull
    private Mensalidade mensalidade;

}
