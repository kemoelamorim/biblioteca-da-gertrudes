package br.com.biblioteca.funcionario.service;

import br.com.biblioteca.funcionario.model.Funcionario;
import br.com.biblioteca.funcionario.model.FuncionarioDTO;
import br.com.biblioteca.funcionario.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Override
    public Page<Funcionario> findAll(Pageable pageable) {
        return this.funcionarioRepository.findAll(pageable);
    }

    @Override
    public Funcionario findById(Long id) {
        if(id != null){
            Optional<Funcionario> funcionarioOptional = this.funcionarioRepository.findById(id);
            if (funcionarioOptional.isPresent()){
                return funcionarioOptional.get();
            }else {
                throw new RuntimeException("Funcionario não encontrado");
            }
        }else {
            throw new RuntimeException("Problema ao buscar funcionario");
        }
    }

    @Override
    public Funcionario saveFuncionario(FuncionarioDTO funcionarioDTO) {
        if(funcionarioDTO != null){
            Funcionario funcionario = this.funcionarioRepository.save(new Funcionario(funcionarioDTO));
            return funcionario;
        }
        throw new RuntimeException("Erro ao salvar um funcionario");
    }

    @Override
    public Funcionario editFuncionario(FuncionarioDTO funcionarioDTO) {
        if(funcionarioDTO != null){
            Optional<Funcionario> funcionarioOptional = this.funcionarioRepository.findById(funcionarioDTO.getId());
            if(funcionarioOptional.isPresent()){
                Funcionario funcionario = new Funcionario(funcionarioDTO);
                return this.funcionarioRepository.save(funcionario);
            }else {
                throw new RuntimeException("Funcionario não encontrado");
            }
        } else {
            throw new RuntimeException("Problema ao editar o funcionario");
        }
    }

    @Override
    public void deleteFuncionario(Long id) {
        if(id != null){
            Optional<Funcionario> funcionarioOptional = this.funcionarioRepository.findById(id);
            if( funcionarioOptional.isPresent()){
                this.funcionarioRepository.delete(funcionarioOptional.get());
            }else {
                throw new RuntimeException("Funcionario não encontrado");
            }
        }
    }

    @Override
    public List<Object[]> cepComMaiorIncidencia() {
        List<Object[]> incedendesCEP = this.funcionarioRepository.buscarCepComMaiorIncidencia();
        return incedendesCEP;
    }
}
