package com.academia.ltd.enderecos;

import lombok.Getter;

@Getter
public class EnderecoResponse {
    private Long id;
    private String rua;
    private String bairro;
    private String cep;
    private String referencia;
    private String clienteCpf;

    public EnderecoResponse(Endereco endereco) {
        this.id = endereco.getId();
        this.rua = endereco.getRua();
        this.bairro = endereco.getBairro();
        this.cep = endereco.getCep();
        this.referencia = endereco.getReferencia();
        this.clienteCpf = endereco.getCliente().getCpf();
    }
}
