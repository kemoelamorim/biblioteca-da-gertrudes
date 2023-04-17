package br.com.biblioteca.cliente.service;

import br.com.biblioteca.cliente.model.Cliente;
import br.com.biblioteca.cliente.model.ClienteDTO;

public interface ClienteService {
    public Cliente saveCliente(ClienteDTO clienteDTO);
}
