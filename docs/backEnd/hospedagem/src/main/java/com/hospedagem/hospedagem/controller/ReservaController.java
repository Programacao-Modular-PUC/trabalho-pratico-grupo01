package com.hospedagem.hospedagem.controller;

import com.hospedagem.hospedagem.DTO.ReservaRequestDTO;
import com.hospedagem.hospedagem.model.Reserva;
import com.hospedagem.hospedagem.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> listarTodos() {
        return reservaService.listarTodos();
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Reserva> listarPorCliente(@PathVariable Integer clienteId) {
        return reservaService.listarPorCliente(clienteId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(reservaService.buscar(id));
    }

    @PostMapping
    public ResponseEntity<Reserva> cadastrar(@RequestBody @Valid ReservaRequestDTO dto) {
        return ResponseEntity.status(201).body(reservaService.cadastrar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> atualizar(@PathVariable Integer id,
                                             @RequestBody @Valid ReservaRequestDTO dto) {
        return ResponseEntity.ok(reservaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Integer id) {
        reservaService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}