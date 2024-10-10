package com.academia.ltd.mensalidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mensalidades")
public class MensalidadeController {

    @Autowired
    private MensalidadeService mensalidadeService;

    // Criar nova mensalidade
    @PostMapping
    public ResponseEntity<MensalidadeResponse> criarMensalidade(@RequestBody MensalidadeRequest mensalidadeRequest) {
        try {
            MensalidadeResponse mensalidadeSalva = mensalidadeService.salvarMensalidade(mensalidadeRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensalidadeSalva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Listar todas as mensalidades
    @GetMapping
    public ResponseEntity<List<MensalidadeResponse>> listarMensalidades() {
        List<MensalidadeResponse> mensalidades = mensalidadeService.listarMensalidades();
        return ResponseEntity.ok(mensalidades);
    }

    // Buscar mensalidade por ID
    @GetMapping("/{id}")
    public ResponseEntity<MensalidadeResponse> buscarMensalidadePorId(@PathVariable Long id) {
        Optional<MensalidadeResponse> mensalidade = mensalidadeService.buscarPorId(id);
        return mensalidade.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Atualizar mensalidade por ID
    @PutMapping("/{id}")
    public ResponseEntity<MensalidadeResponse> atualizarMensalidade(@PathVariable Long id, @RequestBody MensalidadeRequest mensalidadeRequest) {
        try {
            MensalidadeResponse mensalidadeAtualizada = mensalidadeService.atualizarMensalidade(id, mensalidadeRequest);
            return ResponseEntity.ok(mensalidadeAtualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Deletar mensalidade por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMensalidade(@PathVariable Long id) {
        boolean deletado = mensalidadeService.deletarPorId(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Buscar mensalidade por CPF
    @GetMapping("cliente/{cpf}")
    public ResponseEntity<List<MensalidadeResponse>> buscarMensalidadesPorCpf(@PathVariable String cpf) {
        List<MensalidadeResponse> mensalidades = mensalidadeService.buscarPorCpf(cpf);
        return ResponseEntity.ok(mensalidades);
    }
}
