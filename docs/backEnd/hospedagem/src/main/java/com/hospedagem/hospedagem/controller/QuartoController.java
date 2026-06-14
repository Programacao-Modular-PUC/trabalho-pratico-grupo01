package com.hospedagem.hospedagem.controller;

import com.hospedagem.hospedagem.model.Quarto;
import com.hospedagem.hospedagem.model.StatusQuarto;
import com.hospedagem.hospedagem.service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/quartos")
public class QuartoController {

    @Autowired
    private QuartoService quartoService;

    @GetMapping
    public List<Quarto> listarTodos() {
        return quartoService.listarTodos();
    }

    @GetMapping("/tipo")
    public List<Quarto> listarPorTipo(@RequestParam String tipo) {
        return quartoService.listarPorTipo(tipo);
    }

    @GetMapping("/ativos")
    public List<Quarto> listarAtivos() {
        return quartoService.listarAtivos();
    }

    @GetMapping("/disponiveis")
    public List<Quarto> listarDisponiveis(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida) {
        return quartoService.listarDisponiveisPorPeriodo(entrada, saida);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quarto> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(quartoService.buscar(id));
    }

    @PostMapping
    public ResponseEntity<Quarto> criar(@RequestBody Quarto quarto) {
        return ResponseEntity.status(201).body(quartoService.salvar(quarto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quarto> atualizar(@PathVariable Integer id, @RequestBody Quarto quarto) {
        return ResponseEntity.ok(quartoService.atualizar(id, quarto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> alterarStatus(@PathVariable Integer id,
                                              @RequestParam StatusQuarto status) {
        quartoService.alterarStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        quartoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}