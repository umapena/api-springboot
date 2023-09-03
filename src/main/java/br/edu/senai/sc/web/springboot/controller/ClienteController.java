package br.edu.senai.sc.web.springboot.controller;

import br.edu.senai.sc.web.springboot.entity.Cliente;
import br.edu.senai.sc.web.springboot.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cliente")
@Slf4j
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Cliente>> getClientes(){
        try{
            List<Cliente> clientes = clienteService.buscarClientes();
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id){
        try{
            Optional<Cliente> cliente = clienteService.buscarCliente(id);
            if(Optional.ofNullable(cliente).isPresent()){
                return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new Cliente(), HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(new Cliente(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody Cliente cliente){
        try{
            clienteService.salvarCliente(cliente);
        }catch (Exception e){
            return new ResponseEntity<>("Erro ao cadastrar cliente.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Cliente cadastrado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> udpate(@RequestBody Cliente cliente, @PathVariable("id") Long id){
        try{
            clienteService.salvarCliente(cliente);
        }catch (Exception e){
            return new ResponseEntity<>("Erro ao atualizar cliente.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Cliente alterado com sucesso!", HttpStatus.OK);
    }

    @PatchMapping("/atualizarEmail/{id}")
    public ResponseEntity<String> updateEmail(@RequestParam String email, @PathVariable("id") Long id){
        try{
            clienteService.alterarEmail(email,id);
        }catch (Exception e){
            return new ResponseEntity<>("Erro ao atualizar e-mail.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("E-mail atualizado com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        try{
            clienteService.excluirCliente(id);
        }catch (Exception e){
            return new ResponseEntity<>("Erro ao excluir cliente.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Cliente excluído com sucesso!", HttpStatus.OK);
    }

}
