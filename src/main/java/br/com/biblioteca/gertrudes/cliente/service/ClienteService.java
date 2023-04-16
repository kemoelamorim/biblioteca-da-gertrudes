package br.com.biblioteca.gertrudes.cliente.service;

import java.util.List;

import br.com.biblioteca.gertrudes.cliente.model.Cliente;
import br.com.biblioteca.gertrudes.cliente.model.ClienteDTO;

public interface ClienteService {
  
  public List<ClienteDTO> findAll();
  public Cliente findBydId(Long id);
  public void deleteCliente(Long id);
  public Cliente saveCliente(ClienteDTO clienteDTO);
  public Cliente editCliente(ClienteDTO clienteDTO);

}
