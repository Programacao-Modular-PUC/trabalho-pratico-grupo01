package com.hospedagem.hospedagem.controller;

import com.hospedagem.hospedagem.model.Cliente;
import com.hospedagem.hospedagem.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteService.buscar(id));
    }

    @GetMapping("/buscar")
    public List<Cliente> buscarPorNome(@RequestParam String nome) {
        return clienteService.buscarPorNome(nome);
    }

    @PostMapping
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) {
        return ResponseEntity.status(201).body(clienteService.salvar(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Integer id,
                                             @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.atualizar(id, cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}