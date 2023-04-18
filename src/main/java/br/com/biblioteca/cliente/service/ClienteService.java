package br.com.biblioteca.cliente.service;

import br.com.biblioteca.cliente.model.Cliente;
import br.com.biblioteca.cliente.model.ClienteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteService {
    public Page<Cliente> findAll(Pageable pageable);
    public Cliente findById(Long id);
    public Cliente saveCliente(ClienteDTO clienteDTO);
    public Cliente editCliente(ClienteDTO clienteDTO);
    public void deleteCliente(Long id);
}
