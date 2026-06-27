package com.hospedagem.hospedagem.controller;

import com.hospedagem.hospedagem.model.Residencia;
import com.hospedagem.hospedagem.service.ResidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residencias")
public class ResidenciaController {
    @Autowired
    private ResidenciaService residenciaService;

    @GetMapping
    public List<Residencia> getResidencias() {
        return residenciaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Residencia> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(residenciaService.buscar(id));
    }

    @PostMapping
    public ResponseEntity<Residencia> cadastrar(@RequestBody Residencia residencia) {
        return ResponseEntity.status(201).body(residenciaService.salvar(residencia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Residencia> atualizar(@PathVariable Integer id, @RequestBody Residencia residencia) {
        return ResponseEntity.ok(residenciaService.atualizar(id, residencia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        residenciaService.excluir(id);
        return ResponseEntity.noContent().build();
    }


}
