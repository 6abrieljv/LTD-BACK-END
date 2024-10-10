package com.academia.ltd.enderecos;

import com.academia.ltd.clientes.Cliente;
import com.academia.ltd.clientes.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Listar todos os endereços por CPF do cliente
    public List<EnderecoResponse> listarPorCliente(String cpf) {
        List<Endereco> enderecos = enderecoRepository.findByClienteCpf(cpf);
        return enderecos.stream().map(EnderecoResponse::new).toList();
    }

    // Buscar endereço por ID
    public Optional<EnderecoResponse> buscarPorId(Long id) {
        return enderecoRepository.findById(id).map(EnderecoResponse::new);
    }

    // Salvar novo endereço
    public EnderecoResponse salvarEndereco(EnderecoRequest enderecoRequest) {
        Cliente cliente = clienteRepository.findById(enderecoRequest.getClienteCpf())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Endereco endereco = new Endereco();
        endereco.setRua(enderecoRequest.getRua());
        endereco.setBairro(enderecoRequest.getBairro());
        endereco.setCep(enderecoRequest.getCep());
        endereco.setReferencia(enderecoRequest.getReferencia());
        endereco.setCliente(cliente);

        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        return new EnderecoResponse(enderecoSalvo);
    }

    // Atualizar endereço
    public EnderecoResponse atualizarEndereco(Long id, EnderecoRequest enderecoAtualizado) {
        Optional<Endereco> enderecoExistente = enderecoRepository.findById(id);
        if (enderecoExistente.isPresent()) {
            Endereco endereco = enderecoExistente.get();
            endereco.setRua(enderecoAtualizado.getRua());
            endereco.setBairro(enderecoAtualizado.getBairro());
            endereco.setCep(enderecoAtualizado.getCep());
            endereco.setReferencia(enderecoAtualizado.getReferencia());

            Endereco enderecoAtualizadoResult = enderecoRepository.save(endereco);
            return new EnderecoResponse(enderecoAtualizadoResult);
        }
        return null;
    }

    // Deletar endereço por ID
    public boolean deletarPorId(Long id) {
        if (enderecoRepository.existsById(id)) {
            enderecoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
