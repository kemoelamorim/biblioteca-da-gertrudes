package br.com.biblioteca.funcionario.service;

import br.com.biblioteca.funcionario.model.Funcionario;
import br.com.biblioteca.funcionario.model.FuncionarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FuncionarioService {
    public Page<Funcionario> findAll(Pageable pageable);
    public Funcionario findById(Long id);
    public Funcionario saveFuncionario (FuncionarioDTO funcionarioDTO);
    public Funcionario editFuncionario (FuncionarioDTO funcionarioDTO);
    public void deleteFuncionario (Long id);
    public List<Object[]> cepComMaiorIncidencia();
}
