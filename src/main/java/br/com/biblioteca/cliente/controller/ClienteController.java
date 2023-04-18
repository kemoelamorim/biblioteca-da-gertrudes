package br.com.biblioteca.cliente.controller;

import br.com.biblioteca.cliente.model.Cliente;
import br.com.biblioteca.cliente.model.ClienteDTO;
import br.com.biblioteca.cliente.service.ClienteServiceImpl;
import br.com.biblioteca.endereco.model.EnderecoDTO;
import br.com.biblioteca.endereco.service.ServiceViaCepImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    ClienteServiceImpl clienteService;
    @Autowired
    ServiceViaCepImpl viaCepService;
    @ApiOperation(value = "Retorna uma lista de clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de clientes"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping
    public ResponseEntity<?> findAll(){
        List<ClienteDTO> clientes = clienteService.findAll();
        return !clientes.isEmpty() ? ResponseEntity.ok(clientes) : ResponseEntity.noContent().build();
    }
    @ApiOperation(value = "Retorna um cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um cliente"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        Cliente cliente = this.clienteService.findById(id);
        return new ResponseEntity<>(new ClienteDTO(cliente), HttpStatus.OK);
    }
    @ApiOperation(value = "Busca o endereco via cep do cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o endereço do cliente"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/endereco/{id}")
    public ResponseEntity<?> endereco(@PathVariable("id") Long id){
        Cliente cliente = this.clienteService.findById(id);
        EnderecoDTO enderecoDTO = this.viaCepService.getEnderecoViaCep(cliente.getCep());
        return new ResponseEntity<>(new EnderecoDTO(enderecoDTO), HttpStatus.OK);
    }
    @ApiOperation(value = "Salva  um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Salva um cliente e retorna um cliente"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping("/save")
    public ResponseEntity<?> salve(@RequestBody ClienteDTO clienteDTO){
        if(clienteDTO != null){
            Cliente cliente = this.clienteService.saveCliente(clienteDTO);
            return new ResponseEntity<>(new ClienteDTO(cliente), HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }
    @ApiOperation(value = "Salva  um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Salva um cliente e retorna um cliente"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ClienteDTO clienteDTO){
        if(clienteDTO != null){
            Cliente cliente = this.clienteService.editCliente(clienteDTO);
            return new ResponseEntity<>(new ClienteDTO(cliente), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }
    @ApiOperation(value = "Exclui um cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente excluido"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(id != null){
            this.clienteService.deleteCliente(id);
            return  ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
