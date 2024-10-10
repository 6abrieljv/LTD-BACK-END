package com.academia.ltd.mensalidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MensalidadeRequest {
    private String clienteCpf;  // CPF do cliente
    private Double valor;
    private String dataVencimento;
    private String status;
}
