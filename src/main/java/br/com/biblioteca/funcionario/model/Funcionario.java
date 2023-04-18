package br.com.biblioteca.funcionario.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TB_FUNCIONARIO")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FUNCIONARIO")
    private Long id;

    @Column(name = "NOME_COMPLETO")
    private String fullname;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "CPF_CNPJ")
    private String cpfCnpj;

    @Column(name = "EMAIL")
    private String email;
    public Funcionario(FuncionarioDTO funcionarioDTO) {
        this.id = funcionarioDTO.getId();
        this.fullname = funcionarioDTO.getFullname();
        this.cep = funcionarioDTO.getCep();
        this.cpfCnpj = funcionarioDTO.getCpfCnpj();
        this.email = funcionarioDTO.getEmail();
    }
}
