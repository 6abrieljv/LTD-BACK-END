package com.academia.ltd.mensalidades;

import com.academia.ltd.clientes.Cliente;
import com.academia.ltd.clientes.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MensalidadeService {

    @Autowired
    private MensalidadeRepository mensalidadeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Salvar uma nova mensalidade
    public MensalidadeResponse salvarMensalidade(MensalidadeRequest request) {
        Cliente cliente = clienteRepository.findById(request.getClienteCpf()).orElseThrow(() -> 
            new IllegalArgumentException("Cliente não encontrado"));
        
        Mensalidade mensalidade = new Mensalidade();
        mensalidade.setCliente(cliente);
        mensalidade.setValor(request.getValor());
        mensalidade.setDataVencimento(request.getDataVencimento());
        mensalidade.setStatus(request.getStatus());
        
        mensalidade = mensalidadeRepository.save(mensalidade);
        return new MensalidadeResponse(mensalidade);
    }

    // Listar todas as mensalidades
    public List<MensalidadeResponse> listarMensalidades() {
        return mensalidadeRepository.findAll().stream()
            .map(MensalidadeResponse::new)
            .collect(Collectors.toList());
    }

    // Buscar mensalidade por ID
    public Optional<MensalidadeResponse> buscarPorId(Long id) {
        return mensalidadeRepository.findById(id).map(MensalidadeResponse::new);
    }

    // Atualizar mensalidade
    public MensalidadeResponse atualizarMensalidade(Long id, MensalidadeRequest request) {
        Mensalidade mensalidade = mensalidadeRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("Mensalidade não encontrada"));

        // Atualizando os campos
        mensalidade.setValor(request.getValor());
        mensalidade.setDataVencimento(request.getDataVencimento());
        mensalidade.setStatus(request.getStatus());
        
        // Salva as alterações
        mensalidade = mensalidadeRepository.save(mensalidade);
        return new MensalidadeResponse(mensalidade);
    }

    // Deletar mensalidade
    public boolean deletarPorId(Long id) {
        if (mensalidadeRepository.existsById(id)) {
            mensalidadeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //Mensalidade por cliente
    public List<MensalidadeResponse> buscarPorCpf(String cpf) {
        return mensalidadeRepository.findByCliente_Cpf(cpf).stream()
            .map(MensalidadeResponse::new)
            .collect(Collectors.toList());
    }
}
