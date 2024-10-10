package com.academia.ltd.presenças;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PresencaResponse {
    private Long id;
    private String clienteCpf;  // String para CPF
    private String dataTreino;
    private String status;

    // Construtor para PresencaResponse com os parâmetros Long, String, String, String
    public PresencaResponse(Long id, String clienteCpf, String dataTreino, String status) {
        this.id = id;
        this.clienteCpf = clienteCpf;
        this.dataTreino = dataTreino;
        this.status = status;
    }
}
