package com.hospedagem.hospedagem.controller;

import com.hospedagem.hospedagem.model.Pagamento;
import com.hospedagem.hospedagem.model.StatusPagamento;
import com.hospedagem.hospedagem.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping("/{id}")
    public Pagamento consultar(@PathVariable Integer id) {
        return pagamentoService.consultar(id);
    }

    @PutMapping("/{id}/status")
    public Pagamento atualizarStatus(@PathVariable Integer id,
                                     @RequestParam StatusPagamento novoStatus) {
        return pagamentoService.atualizarStatus(id, novoStatus);
    }

    @PostMapping
    public Pagamento registrar(@RequestParam Integer reservaId,
                               @RequestParam Double valor,
                               @RequestParam String formaPagamento) {
        return pagamentoService.registrar(reservaId, valor, formaPagamento);
    }
}