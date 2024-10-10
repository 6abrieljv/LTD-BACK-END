package com.academia.ltd.clientes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Listar todos os clientes
    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listarClientes() {
        List<ClienteResponse> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);  // Retorna 200 OK com a lista de clientes
    }

    // Buscar cliente por CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponse> buscarClientePorCpf(@PathVariable String cpf) {
        Optional<ClienteResponse> cliente = clienteService.buscarPorCpf(cpf);
        return cliente.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                      .body(null));  // Retorna 404 se o cliente não for encontrado
    }

    // Criar novo cliente
    @PostMapping
    public ResponseEntity<ClienteResponse> criarCliente(@RequestBody ClienteRequest clienteRequest) {
        try {
            ClienteResponse clienteSalvo = clienteService.salvarCliente(clienteRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body(clienteSalvo);  // Retorna 201 CREATED com o cliente criado
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // Retorna 400 BAD REQUEST em caso de erro
        }
    }

    // Atualizar cliente por CPF
    @PutMapping("/{cpf}")
    public ResponseEntity<ClienteResponse> atualizarCliente(@PathVariable String cpf, @RequestBody ClienteRequest clienteAtualizado) {
        ClienteResponse clienteAtualizadoResult = clienteService.atualizarCliente(cpf, clienteAtualizado);
        if (clienteAtualizadoResult != null) {
            return ResponseEntity.ok(clienteAtualizadoResult);  // Retorna 200 OK com o cliente atualizado
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Retorna 404 NOT FOUND se o cliente não for encontrado
        }
    }

    // Deletar cliente por CPF
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarCliente(@PathVariable String cpf) {
        boolean deletado = clienteService.deletarPorCpf(cpf);
        if (deletado) {
            return ResponseEntity.noContent().build();  // Retorna 204 NO CONTENT após deletar o cliente
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Retorna 404 NOT FOUND se o cliente não for encontrado
        }
    }
}

