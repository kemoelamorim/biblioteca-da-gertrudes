package br.com.biblioteca.gertrudes.cliente.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.gertrudes.cliente.model.Cliente;
import br.com.biblioteca.gertrudes.cliente.model.ClienteDTO;
import br.com.biblioteca.gertrudes.cliente.service.ClienteServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiResponse;

@Slf4j
@RestController
@RequestMapping("/api/cliente")
@CrossOrigin("*")
public class ClienteController {

  private ClienteServiceImpl clienteService;
  
  public ClienteController(){
    clienteService = new ClienteServiceImpl();
  }

  @ApiOperation(value = "Retorna uma lista de clientes")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Retorna uma lista de clientes"),
    @ApiResponse(code = 204, message = "Sem conteúdo"),
    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
  })
  @GetMapping
  public ResponseEntity<?> findAll(){
    log.info("findAll - Cliente");
    List<ClienteDTO> listaClientes = this.clienteService.findAll();
    return !listaClientes.isEmpty() ? ResponseEntity.ok(listaClientes) : ResponseEntity.noContent().build();
  }
  @ApiOperation(value = "Retorna um cliente")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Retorna um cliente"),
    @ApiResponse(code = 204, message = "Sem conteúdo"),
    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
  })
  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable("id") Long id){
    log.info("findById - Cliente");
    Cliente cliente = this.clienteService.findBydId(id);
    return new ResponseEntity<>(new ClienteDTO(cliente), HttpStatus.OK);
  }

  @ApiOperation(value = "Salva um novo cliente")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Salva um cliente e retorna um cliente"),
    @ApiResponse(code = 204, message = "Sem conteúdo"),
    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
  })
  @PostMapping("/save")
  public ResponseEntity<?> save(@RequestBody ClienteDTO clienteDTO){
    log.info("save - Cliente");
    if(clienteDTO != null){
      Cliente cliente = clienteService.saveCliente(clienteDTO);
      return new ResponseEntity<>(new ClienteDTO(cliente), HttpStatus.CREATED);
    }
    return ResponseEntity.badRequest().build();
  }

  @ApiOperation(value = "Edita um novo cliente")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Edita um cliente e retorna um cliente"),
    @ApiResponse(code = 204, message = "Sem conteúdo"),
    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
  })
  @PutMapping("/update")
  public ResponseEntity<?> update(@RequestBody ClienteDTO clienteDTO){
    log.info("update - Cliente");
    if(clienteDTO != null){
      Cliente cliente = clienteService.editCliente(clienteDTO);
      return new ResponseEntity<>(new ClienteDTO(cliente), HttpStatus.OK);
    }
    return ResponseEntity.badRequest().build();
  }

  @ApiOperation(value = "Exclui um cliente")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Exclui um cliente"),
    @ApiResponse(code = 204, message = "Sem conteúdo"),
    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
  })
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id){
    log.info("delete - Cliente");
    if(id != null){
      this.clienteService.deleteCliente(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.badRequest().build();
  }
}
