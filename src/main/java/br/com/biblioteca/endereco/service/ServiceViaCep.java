package br.com.biblioteca.endereco.service;

import br.com.biblioteca.endereco.model.EnderecoDTO;

public interface ServiceViaCep {

    public EnderecoDTO getEnderecoViaCep(String cep);
}
