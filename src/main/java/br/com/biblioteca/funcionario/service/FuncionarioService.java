package br.com.biblioteca.funcionario.service;

import br.com.biblioteca.funcionario.model.Funcionario;
import br.com.biblioteca.funcionario.model.FuncionarioDTO;

import java.util.List;

public interface FuncionarioService {
    public List<FuncionarioDTO> findAll();
    public Funcionario findById(Long id);
    public Funcionario saveFuncionario (FuncionarioDTO funcionarioDTO);
    public Funcionario editFuncionario (FuncionarioDTO funcionarioDTO);
    public void deleteFuncionario (Long id);
    public List<Object[]> cepComMaiorIncidencia();
}
