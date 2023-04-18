package br.com.biblioteca.funcionario.model;

import br.com.biblioteca.cliente.model.Cliente;
import br.com.biblioteca.cliente.model.ClienteDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class FuncionarioDTO {
    private Long id;
    private String fullname;
    private String cep;
    private String cpfCnpj;
    private String email;
    public FuncionarioDTO(Funcionario funcionario) {
        if(funcionario != null) {
            this.id = funcionario.getId();
            this.fullname = funcionario.getFullname();
            this.cep = funcionario.getCep();
            this.cpfCnpj = funcionario.getCpfCnpj();
            this.email = funcionario.getEmail();
        }
    }

    public static List<FuncionarioDTO> converter(List<Funcionario> funcionario) {
        return funcionario.stream().map(FuncionarioDTO::new).collect(Collectors.toList());
    }
}
