package br.com.biblioteca.cliente.controller;

import br.com.biblioteca.cliente.model.Cliente;
import br.com.biblioteca.cliente.model.ClienteDTO;
import br.com.biblioteca.cliente.service.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    ClienteServiceImpl clienteService;

    @GetMapping("hello")
    public String hello(){
        return "Hello wolrd";
    }
    @PostMapping("/save")
    public ResponseEntity<?> salve(@RequestBody ClienteDTO clienteDTO){
        if(clienteDTO != null){
            Cliente cliente = this.clienteService.saveCliente(clienteDTO);
            return new ResponseEntity<>(new ClienteDTO(cliente), HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

}
