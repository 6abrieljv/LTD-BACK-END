package com.academia.ltd.clientes;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Validação de formato de e-mail com regex
    private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);

    // Listar todos os clientes
    public List<ClienteResponse> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(ClienteResponse::new).toList();
    }

    // Buscar cliente por CPF
    public Optional<ClienteResponse> buscarPorCpf(String cpf) {
        return clienteRepository.findById(cpf).map(ClienteResponse::new);
    }

    // Criar ou salvar cliente
    public ClienteResponse salvarCliente(ClienteRequest clienteRequest) {
        // Verificar se o CPF é válido
        if (!isCpfValido(clienteRequest.getCpf())) {
            throw new IllegalArgumentException("CPF inválido");
        }

        // Verificar se o e-mail é válido
        if (!isEmailValido(clienteRequest.getEmail())) {
            throw new IllegalArgumentException("E-mail inválido");
        }

        // Verificar se o CPF já está em uso
        if (clienteRepository.findById(clienteRequest.getCpf()).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        // Verificar se o e-mail já está em uso
        if (clienteRepository.existsByEmail(clienteRequest.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        // Mapeando os dados do ClienteRequest para uma nova instância de Cliente
        Cliente cliente = new Cliente();
        cliente.setCpf(clienteRequest.getCpf());
        cliente.setNome(clienteRequest.getNome());
        cliente.setEmail(clienteRequest.getEmail());
        cliente.setTelefone(clienteRequest.getTelefone());
        cliente.setDataNascimento(clienteRequest.getDataNascimento());
        cliente.setEnderecos(clienteRequest.getEnderecos());
        cliente.setMensalidade(clienteRequest.getMensalidade());

        // Salvando o cliente no repositório
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return new ClienteResponse(clienteSalvo);
    }

    // Atualizar cliente
    public ClienteResponse atualizarCliente(String cpf, ClienteRequest clienteAtualizado) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(cpf);
        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();

            // Validação de CPF não precisa ser feita aqui, pois o CPF já é do cliente
            // Validar e-mail se ele for alterado
            if (!cliente.getEmail().equals(clienteAtualizado.getEmail())) {
                if (!isEmailValido(clienteAtualizado.getEmail())) {
                    throw new IllegalArgumentException("E-mail inválido");
                }
                if (clienteRepository.existsByEmail(clienteAtualizado.getEmail())) {
                    throw new IllegalArgumentException("E-mail já cadastrado");
                }
            }

            cliente.setNome(clienteAtualizado.getNome());
            cliente.setEmail(clienteAtualizado.getEmail());
            cliente.setTelefone(clienteAtualizado.getTelefone());
            cliente.setDataNascimento(clienteAtualizado.getDataNascimento());
            cliente.setEnderecos(clienteAtualizado.getEnderecos());
            cliente.setMensalidade(clienteAtualizado.getMensalidade());

            Cliente clienteAtualizadoResult = clienteRepository.save(cliente);
            return new ClienteResponse(clienteAtualizadoResult);
        }
        return null;
    }

    // Deletar cliente por CPF
    public boolean deletarPorCpf(String cpf) {
        if (clienteRepository.existsById(cpf)) {
            clienteRepository.deleteById(cpf);
            return true;
        }
        return false;
    }

    // Método para validar o formato de e-mail
    private boolean isEmailValido(String email) {
        return emailPattern.matcher(email).matches();
    }

    // Método para validar o CPF
    public boolean isCpfValido(String cpf) {
        // Remover pontuações (tratar os CPFs com formatação)
        cpf = cpf.replaceAll("\\D", "");

        // Verificar se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verificar se todos os dígitos são iguais (CPFs inválidos como 111.111.111-11)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Cálculo dos dígitos verificadores
        int[] pesoCPF = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * pesoCPF[i];
        }
        int primeiroDigitoVerificador = 11 - (soma % 11);
        if (primeiroDigitoVerificador > 9) {
            primeiroDigitoVerificador = 0;
        }

        soma = 0;
        int[] pesoCPF2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * pesoCPF2[i];
        }
        int segundoDigitoVerificador = 11 - (soma % 11);
        if (segundoDigitoVerificador > 9) {
            segundoDigitoVerificador = 0;
        }

        // Comparar os dígitos verificadores
        return cpf.charAt(9) - '0' == primeiroDigitoVerificador &&
               cpf.charAt(10) - '0' == segundoDigitoVerificador;
    }
}
