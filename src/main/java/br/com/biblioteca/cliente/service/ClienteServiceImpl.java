package br.com.biblioteca.cliente.service;

import br.com.biblioteca.cliente.model.Cliente;
import br.com.biblioteca.cliente.model.ClienteDTO;
import br.com.biblioteca.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public Cliente saveCliente(ClienteDTO clienteDTO) {
        if(clienteDTO != null){
            Cliente cliente = this.clienteRepository.save(new Cliente(clienteDTO));
            return cliente;
        }
        throw new RuntimeException("Erro ao salvar um cliente");
    }
}
