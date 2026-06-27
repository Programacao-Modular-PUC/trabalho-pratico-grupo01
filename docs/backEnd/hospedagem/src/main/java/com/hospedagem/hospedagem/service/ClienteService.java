package com.hospedagem.hospedagem.service;

import com.hospedagem.hospedagem.model.Cliente;
import com.hospedagem.hospedagem.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    public Cliente buscar(Integer id) {
        return repository.findById(id)
                .orElse(null);
    }

    public Cliente salvar(Cliente cliente) {
        if (repository.existsByCpf(cliente.getCpf())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado");
        }
        if (repository.existsByEmail(cliente.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
        }
        return repository.save(cliente);
    }

    public Cliente atualizar(Integer id, Cliente dados) {
        Cliente existente = buscar(id);
        // valida CPF duplicado ignorando o próprio cliente
        repository.findByCpf(dados.getCpf())
                .ifPresent(c -> {
                    if (c.getId() != id) {
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado");
                    }
                });

        existente.setNome(dados.getNome());
        existente.setCpf(dados.getCpf());
        existente.setEndereco(dados.getEndereco());
        existente.setTelefone(dados.getTelefone());
        existente.setEmail(dados.getEmail());
        return repository.save(existente);
    }

    public void excluir(Integer id) {
        buscar(id);
        repository.deleteById(id);
    }

    public List<Cliente> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}