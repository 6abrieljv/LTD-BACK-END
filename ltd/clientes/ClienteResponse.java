package com.academia.ltd.clientes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {

    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private String dataNascimento;

    public ClienteResponse(Cliente cliente) {
        this.cpf = cliente.getCpf();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone(); 
        this.dataNascimento = cliente.getDataNascimento(); 
    }
}
