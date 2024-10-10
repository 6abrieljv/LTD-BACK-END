package com.academia.ltd.presenças;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PresencaRequest {
    private String clienteCpf;    // Identificador é o CPF
    private String dataTreino;
    private String status;

   
    public String getClienteCpf() {
        return clienteCpf;
    }
}
