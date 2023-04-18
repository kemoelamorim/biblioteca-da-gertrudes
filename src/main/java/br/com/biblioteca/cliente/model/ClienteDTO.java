package br.com.biblioteca.cliente.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO {
    private Long id;
    private String fullname;
    private String cep;
    private String cpfCnpj;
    private String email;
    public ClienteDTO(Cliente cliente) {
        if(cliente != null) {
            this.id = cliente.getId();
            this.fullname = cliente.getFullname();
            this.cep = cliente.getCep();
            this.cpfCnpj = cliente.getCpfCnpj();
            this.email = cliente.getEmail();
        }
    }

    public static List<ClienteDTO> converter(List<Cliente> clientes) {
        return clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
    }
}
