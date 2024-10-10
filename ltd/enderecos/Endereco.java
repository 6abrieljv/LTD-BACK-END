package com.academia.ltd.enderecos;

import com.academia.ltd.clientes.Cliente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Tb_Endereco")
@Entity(name = "Endereco")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Endereco {
    
    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @NotNull
    @JoinColumn(name = "cliente_cpf", referencedColumnName = "cpf")
    private Cliente cliente;
    
    @Column(name = "rua")
    @NotNull
    private String rua;
    
    @Column(name = "bairro")
    @NotNull
    private String bairro;
    
    @Column(name = "cep")
    @NotNull
    private String cep;
    
    @Column(name = "referencia")
    @NotNull
    private String referencia;
}
