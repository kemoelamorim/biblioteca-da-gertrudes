package br.com.biblioteca.funcionario.controller;

import br.com.biblioteca.endereco.model.EnderecoDTO;
import br.com.biblioteca.endereco.service.ServiceViaCepImpl;
import br.com.biblioteca.funcionario.model.Funcionario;
import br.com.biblioteca.funcionario.model.FuncionarioDTO;
import br.com.biblioteca.funcionario.service.FuncionarioServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/funcionario")
public class FuncionarioController {
    @Autowired
    FuncionarioServiceImpl funcionarioService;
    @Autowired
    ServiceViaCepImpl viaCepService;

    @ApiOperation(value = "Retorna uma lista de funcionarios")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de funcionarios"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping
    public ResponseEntity<?> findAll(){
        List<FuncionarioDTO> funcionarios = funcionarioService.findAll();
        return !funcionarios.isEmpty() ? ResponseEntity.ok(funcionarios) : ResponseEntity.noContent().build();
    }
    @ApiOperation(value = "Retorna o cep com maior incedencia")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o cep com maior incidencia"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("cep/maiorincidencia")
    public ResponseEntity<?> cepMaiorIncidencia(){
        List<Object[]> cep = funcionarioService.cepComMaiorIncidencia();
        List<Map<String, Object>> lista = new ArrayList<>();
        for (Object[] par : cep) {
            Map<String, Object> objeto = new HashMap<>();
            objeto.put("cep", par[0]);
            objeto.put("qtd", par[1]);
            lista.add(objeto);
        }
        return !lista.isEmpty() ? ResponseEntity.ok(lista) : ResponseEntity.noContent().build();
    }
    @ApiOperation(value = "Retorna um funcionario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um funcionario"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        Funcionario funcionario = this.funcionarioService.findById(id);
        return new ResponseEntity<>(new FuncionarioDTO(funcionario), HttpStatus.OK);
    }

    @ApiOperation(value = "Busca o endereco via cep do funcionario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o endereço do funcionario"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/endereco/{id}")
    public ResponseEntity<?> endereco(@PathVariable("id") Long id){
        Funcionario funcionario = this.funcionarioService.findById(id);
        EnderecoDTO enderecoDTO = this.viaCepService.getEnderecoViaCep(funcionario.getCep());
        return new ResponseEntity<>(new EnderecoDTO(enderecoDTO), HttpStatus.OK);
    }
    @ApiOperation(value = "Salva  um novo funcionario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Salva um cliente e retorna um funcionario"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping("/save")
    public ResponseEntity<?> salve(@RequestBody FuncionarioDTO funcionarioDTO){
        if(funcionarioDTO != null){
            Funcionario funcionario = this.funcionarioService.saveFuncionario(funcionarioDTO);
            return new ResponseEntity<>(new FuncionarioDTO(funcionario), HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }
    @ApiOperation(value = "Salva  um novo funcionario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Salva um funcionario e retorna o funcionario"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody FuncionarioDTO funcionarioDTO){
        if(funcionarioDTO != null){
            Funcionario funcionario = this.funcionarioService.editFuncionario(funcionarioDTO);
            return new ResponseEntity<>(new FuncionarioDTO(funcionario), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }
    @ApiOperation(value = "Exclui um funcionario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Funcionario excluido"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(id != null){
            this.funcionarioService.deleteFuncionario(id);
            return  ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
