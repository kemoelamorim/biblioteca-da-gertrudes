package br.com.biblioteca.endereco.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoDTO {

    private String cep;
    private String praçaDaSé;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
    public EnderecoDTO(EnderecoDTO enderecoDTO){
        this.cep = enderecoDTO.getCep();
        this.praçaDaSé = enderecoDTO.getPraçaDaSé();
        this.complemento = enderecoDTO.getComplemento();
        this.bairro = enderecoDTO.getBairro();
        this.localidade = enderecoDTO.getLocalidade();
        this.uf = enderecoDTO.getUf();
        this.ibge = enderecoDTO.getIbge();
        this.gia = enderecoDTO.getGia();
        this.ddd = enderecoDTO.getDdd();
        this.siafi = enderecoDTO.getSiafi();
    }

}
