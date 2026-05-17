package com.hospedagem.hospedagem.controller;

import com.hospedagem.hospedagem.model.Relatorio;
import com.hospedagem.hospedagem.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping
    public List<Relatorio> listarTodos() {
        return relatorioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Relatorio> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(relatorioService.buscarPeloId(id));
    }

    @PostMapping
    public ResponseEntity<Relatorio> inserir(@RequestBody Relatorio relatorio) {
        return ResponseEntity.status(201).body(relatorioService.salvar(relatorio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Relatorio> alterar(@PathVariable Integer id,
                                             @RequestBody Relatorio relatorio) {
        return ResponseEntity.ok(relatorioService.atualizar(id, relatorio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        relatorioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}