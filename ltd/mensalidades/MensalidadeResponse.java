package com.academia.ltd.mensalidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MensalidadeResponse {
    private Long id;
    private String clienteCpf;
    private Double valor;
    private String dataVencimento;
    private String status;

    public MensalidadeResponse(Mensalidade mensalidade) {
        this.id = mensalidade.getId();
        this.clienteCpf = mensalidade.getCliente().getCpf();
        this.valor = mensalidade.getValor();
        this.dataVencimento = mensalidade.getDataVencimento();
        this.status = mensalidade.getStatus();
    }
}
