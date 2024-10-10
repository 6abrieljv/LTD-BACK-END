package com.academia.ltd.presenças;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/presencas")
public class PresencaController {

    @Autowired
    private PresencaService presencaService;

    // Criar nova presença
    @PostMapping
    public ResponseEntity<PresencaResponse> criarPresenca(@RequestBody PresencaRequest presencaRequest) {
        try {
            PresencaResponse presencaSalva = presencaService.salvarPresenca(presencaRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(presencaSalva);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Listar todas as presenças
    @GetMapping
    public ResponseEntity<List<PresencaResponse>> listarPresencas() {
        List<PresencaResponse> presencas = presencaService.listarPresencas();
        return ResponseEntity.ok(presencas);
    }

    // Buscar presença por ID
    @GetMapping("/{id}")
    public ResponseEntity<PresencaResponse> buscarPresencaPorId(@PathVariable Long id) {
        Optional<PresencaResponse> presenca = presencaService.buscarPorId(id);
        return presenca.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Buscar presença por CPF do cliente
    @GetMapping("/cliente/{cpf}")
    public ResponseEntity<List<PresencaResponse>> buscarPresencaPorCpf(@PathVariable String cpf) {
        List<PresencaResponse> presencas = presencaService.buscarPorCpf(cpf);
        if (presencas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(presencas);
    }

    // Atualizar presença por ID
    @PutMapping("/{id}")
    public ResponseEntity<PresencaResponse> atualizarPresenca(@PathVariable Long id, @RequestBody PresencaRequest presencaRequest) {
        try {
            PresencaResponse presencaAtualizada = presencaService.atualizarPresenca(id, presencaRequest);
            return ResponseEntity.ok(presencaAtualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Deletar presença por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPresenca(@PathVariable Long id) {
        boolean deletado = presencaService.deletarPresenca(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Deletar presença por CPF do cliente
    @DeleteMapping("/cliente/{cpf}")
    public ResponseEntity<Void> deletarPresencaPorCpf(@PathVariable String cpf) {
        boolean deletado = presencaService.deletarPorCpf(cpf);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
