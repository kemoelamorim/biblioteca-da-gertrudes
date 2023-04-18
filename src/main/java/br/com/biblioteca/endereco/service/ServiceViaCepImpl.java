package br.com.biblioteca.endereco.service;

import br.com.biblioteca.endereco.model.EnderecoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class ServiceViaCepImpl implements ServiceViaCep{
    private static final String URL_BASE = "https://viacep.com.br/ws/";

    @Override
    public EnderecoDTO getEnderecoViaCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = URL_BASE + cep + "/json";
        EnderecoDTO endereco = restTemplate.getForObject(url, EnderecoDTO.class);
        return endereco;
    }
}
