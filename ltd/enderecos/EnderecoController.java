package com.academia.ltd.enderecos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    // Listar todos os endereços de um cliente
    @GetMapping("/cliente/{cpf}")
    public ResponseEntity<List<EnderecoResponse>> listarEnderecosPorCliente(@PathVariable String cpf) {
        List<EnderecoResponse> enderecos = enderecoService.listarPorCliente(cpf);
        return ResponseEntity.ok(enderecos);
    }

    // Buscar endereço por ID
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponse> buscarEnderecoPorId(@PathVariable Long id) {
        Optional<EnderecoResponse> endereco = enderecoService.buscarPorId(id);
        return endereco.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Criar novo endereço
    @PostMapping
    public ResponseEntity<EnderecoResponse> criarEndereco(@RequestBody EnderecoRequest enderecoRequest) {
        try {
            EnderecoResponse enderecoSalvo = enderecoService.salvarEndereco(enderecoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(enderecoSalvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Atualizar endereço por ID
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponse> atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoRequest enderecoAtualizado) {
        EnderecoResponse enderecoAtualizadoResult = enderecoService.atualizarEndereco(id, enderecoAtualizado);
        if (enderecoAtualizadoResult != null) {
            return ResponseEntity.ok(enderecoAtualizadoResult);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Deletar endereço por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
        boolean deletado = enderecoService.deletarPorId(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

