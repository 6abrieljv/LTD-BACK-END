package com.academia.ltd.presenças;

import com.academia.ltd.clientes.Cliente;
import com.academia.ltd.clientes.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PresencaService {

    @Autowired
    private PresencaRepository presencaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Criar uma nova presença
    public PresencaResponse salvarPresenca(PresencaRequest presencaRequest) {
        Optional<Cliente> cliente = clienteRepository.findById(presencaRequest.getClienteCpf());

        if (cliente.isPresent()) {
            Presenca presenca = new Presenca();
            presenca.setCliente(cliente.get());
            presenca.setDataTreino(presencaRequest.getDataTreino());
            presenca.setStatus(presencaRequest.getStatus());

            presencaRepository.save(presenca);

            return new PresencaResponse(presenca.getId(), cliente.get().getCpf(),  // Retorna o CPF do cliente
                    presenca.getDataTreino(), presenca.getStatus());
        } else {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
    }

    // Listar todas as presenças
    public List<PresencaResponse> listarPresencas() {
        List<Presenca> presencas = presencaRepository.findAll();
        return presencas.stream()
                .map(p -> new PresencaResponse(p.getId(), p.getCliente().getCpf(), p.getDataTreino(), p.getStatus()))
                .toList();
    }

    // Buscar presença por ID
    public Optional<PresencaResponse> buscarPorId(Long id) {
        return presencaRepository.findById(id)
                .map(p -> new PresencaResponse(p.getId(), p.getCliente().getCpf(), p.getDataTreino(), p.getStatus()));
    }

    // Buscar presenças por CPF do cliente
    public List<PresencaResponse> buscarPorCpf(String cpf) {
        List<Presenca> presencas = presencaRepository.findByClienteCpf(cpf);
        return presencas.stream()
                .map(p -> new PresencaResponse(p.getId(), p.getCliente().getCpf(), p.getDataTreino(), p.getStatus()))
                .toList();
    }

    // Atualizar presença por ID ou CPF
    public PresencaResponse atualizarPresenca(Long id, PresencaRequest presencaRequest) {
        Optional<Presenca> presencaExistente = presencaRepository.findById(id);
        Optional<Cliente> cliente = clienteRepository.findById(presencaRequest.getClienteCpf());

        if (presencaExistente.isPresent() && cliente.isPresent()) {
            Presenca presenca = presencaExistente.get();
            presenca.setCliente(cliente.get());
            presenca.setDataTreino(presencaRequest.getDataTreino());
            presenca.setStatus(presencaRequest.getStatus());

            presencaRepository.save(presenca);

            return new PresencaResponse(presenca.getId(), cliente.get().getCpf(),
                    presenca.getDataTreino(), presenca.getStatus());
        } else {
            throw new IllegalArgumentException("Presença ou Cliente não encontrado");
        }
    }

    // Atualizar presença por CPF
    public PresencaResponse atualizarPorCpf(String cpf, PresencaRequest presencaRequest) {
        List<Presenca> presencas = presencaRepository.findByClienteCpf(cpf);
        Optional<Cliente> cliente = clienteRepository.findById(presencaRequest.getClienteCpf());

        if (!presencas.isEmpty() && cliente.isPresent()) {
            Presenca presenca = presencas.get(0);  
            presenca.setCliente(cliente.get());
            presenca.setDataTreino(presencaRequest.getDataTreino());
            presenca.setStatus(presencaRequest.getStatus());

            presencaRepository.save(presenca);

            return new PresencaResponse(presenca.getId(), cliente.get().getCpf(),
                    presenca.getDataTreino(), presenca.getStatus());
        } else {
            throw new IllegalArgumentException("Presença ou Cliente não encontrado");
        }
    }

    // Deletar presença por ID
    public boolean deletarPresenca(Long id) {
        if (presencaRepository.existsById(id)) {
            presencaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Deletar presença por CPF do cliente
    public boolean deletarPorCpf(String cpf) {
        if (presencaRepository.existsByClienteCpf(cpf)) {
            presencaRepository.deleteByClienteCpf(cpf);
            return true;
        }
        return false;
    }
}
