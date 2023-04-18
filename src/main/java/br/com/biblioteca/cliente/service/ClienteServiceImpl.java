package br.com.biblioteca.cliente.service;

import br.com.biblioteca.cliente.model.Cliente;
import br.com.biblioteca.cliente.model.ClienteDTO;
import br.com.biblioteca.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    ClienteRepository clienteRepository;
    @Override
    public List<ClienteDTO> findAll() {
        List<Cliente> clientes = this.clienteRepository.findAll();
        return ClienteDTO.converter(clientes);
    }

    @Override
    public Cliente findById(Long id) {
        if(id != null){
            Optional<Cliente> clienteOptional = this.clienteRepository.findById(id);
            if (clienteOptional.isPresent()){
                return clienteOptional.get();
            }else {
                throw new RuntimeException("Cliente não encontrado");
            }
        }else {
            throw new RuntimeException("Problema ao buscar cliente");
        }
    }

    @Override
    public Cliente saveCliente(ClienteDTO clienteDTO) {
        if(clienteDTO != null){
            Cliente cliente = this.clienteRepository.save(new Cliente(clienteDTO));
            return cliente;
        }
        throw new RuntimeException("Erro ao salvar um cliente");
    }
    @Override
    public Cliente editCliente(ClienteDTO clienteDTO) {
        if(clienteDTO != null){
            Optional<Cliente> clienteOptional = this.clienteRepository.findById(clienteDTO.getId());
            if(clienteOptional.isPresent()){
                Cliente cliente = new Cliente(clienteDTO);
                return this.clienteRepository.save(cliente);
            }else {
                throw new RuntimeException("Cliente não encontrado");
            }
        } else {
            throw new RuntimeException("Problema ao editar o cliente");
        }
    }
    @Override
    public void deleteCliente(Long id) {
        if(id != null){
            Optional<Cliente> clienteOptional = this.clienteRepository.findById(id);
            if( clienteOptional.isPresent()){
                this.clienteRepository.delete(clienteOptional.get());
            }else {
                throw new RuntimeException("Cliente não encontrado");
            }
        }
    }
}
