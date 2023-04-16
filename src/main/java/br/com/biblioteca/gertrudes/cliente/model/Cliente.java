package br.com.biblioteca.gertrudes.cliente.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TB_CLIENTE")
public class Cliente implements Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_CLIENTE")
  private Long id;

  @Column(name = "NOME")
  private String nome;

  @Column(name = "RAZAO_SOCIAL")
  private String razaoSocial;

  @Column(name = "CPF_CNPJ")
  private String cpfCnpj;

  @Column(name = "EMAIL")
  private String email;

  public Cliente(ClienteDTO clienteDTO) {
    this.id = clienteDTO.getId();
    this.nome = clienteDTO.getNome();
    this.razaoSocial = clienteDTO.getRazaoSocial();
    this.cpfCnpj = clienteDTO.getCpfCnpj();
    this.email = clienteDTO.getEmail();
  }
}
