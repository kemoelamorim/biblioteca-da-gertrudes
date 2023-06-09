package br.com.biblioteca.cliente.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TB_CLIENTE")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Long id;

    @Column(name = "NOME_COMPLETO")
    private String fullname;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "CPF_CNPJ")
    private String cpfCnpj;

    @Column(name = "EMAIL")
    private String email;
    public Cliente(ClienteDTO clienteDTO) {
        this.id = clienteDTO.getId();
        this.fullname = clienteDTO.getFullname();
        this.cep = clienteDTO.getCep();
        this.cpfCnpj = clienteDTO.getCpfCnpj();
        this.email = clienteDTO.getEmail();
    }
}
