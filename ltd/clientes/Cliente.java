package com.academia.ltd.clientes;

import com.academia.ltd.enderecos.Endereco;
import com.academia.ltd.mensalidades.Mensalidade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "Tb_Clientes")
@Entity(name = "Clientes")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cpf")
@Getter
@Setter
public class Cliente {

    @Id
    @NotNull
    @Column(name = "cpf")
    private String cpf;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "telefone")
    private String telefone;  

    @NotNull
    @Column(name = "data_nascimento")
    private String dataNascimento;

    // Relacionamento com endereços, utilizando cascade para persistir e deletar automaticamente os endereços relacionados
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    // Relacionamento com mensalidade referenciando pelo CPF
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Mensalidade mensalidade;  // O lado 'owning' do relacionamento está na tabela Mensalidade (usando id_cliente como chave estrangeira)
}
