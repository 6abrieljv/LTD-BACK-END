package com.academia.ltd.enderecos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequest {
    private String rua;
    private String bairro;
    private String cep;
    private String referencia;
    private String clienteCpf;
}
