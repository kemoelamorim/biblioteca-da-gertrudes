package br.com.biblioteca.cliente.service;

import br.com.biblioteca.cliente.model.Cliente;
import br.com.biblioteca.cliente.model.ClienteDTO;

import java.util.List;

public interface ClienteService {
    public List<ClienteDTO> findAll();
    public Cliente findById(Long id);
    public Cliente saveCliente(ClienteDTO clienteDTO);
    public Cliente editCliente(ClienteDTO clienteDTO);
    public void deleteCliente(Long id);
}
