package com.academia.ltd.mensalidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.academia.ltd.clientes.Cliente;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "Tb_Mensalidade")
public class Mensalidade {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Relacionamento OneToOne com Cliente, utilizando id_cliente como chave estrangeira
    @OneToOne
    @NotNull
    @JoinColumn(name = "cliente_cpf", referencedColumnName = "cpf")  // Chave estrangeira id_cliente faz referência ao cpf da tabela Cliente
    private Cliente cliente;

    @Column(name = "valor")
    @NotNull
    private Double valor;

    @Column(name = "data_vencimento")
    @NotNull
    private String dataVencimento;
    
    @Column(name = "status")
    @NotNull
    private String status;


}
