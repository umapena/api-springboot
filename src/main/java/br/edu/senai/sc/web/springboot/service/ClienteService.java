package br.edu.senai.sc.web.springboot.service;

import br.edu.senai.sc.web.springboot.entity.Cliente;
import br.edu.senai.sc.web.springboot.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvarCliente(Cliente cliente){
        clienteRepository.save(cliente);
        return cliente;
    }

    public void excluirCliente(Long id){
        clienteRepository.deleteById(id);
    }

    public Optional<Cliente> buscarCliente(Long id){
        return clienteRepository.findById(id);
    }

    public List<Cliente> buscarClientes(){
        return clienteRepository.findAll();
    }

    public void atualizar(Cliente cliente, Long id){
        Optional<Cliente> c = clienteRepository.findById(id);
        if(Optional.ofNullable(cliente).isPresent()){
            c.get().setNome(cliente.getNome());
            c.get().setCpf(cliente.getCpf());
            c.get().setEmail(cliente.getEmail());
            c.get().setTelefone(cliente.getTelefone());
            clienteRepository.save(c.get());
        }
    }
    public void atualizarEmail(String email, Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(Optional.ofNullable(cliente).isPresent()){
            cliente.get().setEmail(email);
            clienteRepository.save(cliente.get());
        }
    }

}
