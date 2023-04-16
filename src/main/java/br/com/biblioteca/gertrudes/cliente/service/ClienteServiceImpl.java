package br.com.biblioteca.gertrudes.cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.gertrudes.cliente.model.Cliente;
import br.com.biblioteca.gertrudes.cliente.model.ClienteDTO;
import br.com.biblioteca.gertrudes.cliente.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Override
  public void deleteCliente(Long id) {
    if(id != null){
      Optional<Cliente> clienteOptional = this.clienteRepository.findById(id);
      if(clienteOptional.isPresent()){
        this.clienteRepository.delete(clienteOptional.get());
      }else{
        throw new RuntimeException("Cliente não encontrado.");
      }
    }    
  }

  @Override
  public Cliente editCliente(ClienteDTO clienteDTO) {
    if(clienteDTO != null){
      Optional<Cliente> clienteOptional = this.clienteRepository.findById(clienteDTO.getId());
      if(clienteOptional.isPresent()){
        Cliente cliente = new Cliente(clienteDTO);
        return this.clienteRepository.save(cliente);
      }else{
        throw new RuntimeException("Cliente não encontrado.");
      }
    }else{
      throw new RuntimeException("Problema ao editar cliente.");
    }
  }

  @Override
  public List<ClienteDTO> findAll() {
    List<Cliente> clientes = this.clienteRepository.findAll();
    return ClienteDTO.converter(clientes);
  }

  @Override
  public Cliente findBydId(Long id) {
    if(id != null){
      Optional<Cliente> clienteOptional = this.clienteRepository.findById(id);
      if(clienteOptional.isPresent()){
        return clienteOptional.get();
      }else{
        throw new RuntimeException("Cliente não encontrado.");
      }
    } else {
      throw new RuntimeException("Problema ao buscar cliente.");
    }
  }

  @Override
  public Cliente saveCliente(ClienteDTO clienteDTO) {
    if(clienteDTO != null){
      System.out.println("#################### aqi" + clienteDTO.getNome());
      return this.clienteRepository.save(new Cliente(clienteDTO));
    }
    throw new RuntimeException("Problema ao salvar cliente.");
  }
}
