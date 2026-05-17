package com.hospedagem.hospedagem.controller;

import com.hospedagem.hospedagem.model.LogAuditoria;
import com.hospedagem.hospedagem.service.LogAuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogAuditoriaController {

    @Autowired
    private LogAuditoriaService logAuditoriaService;

    @GetMapping
    public List<LogAuditoria> buscarTodos() {
        return logAuditoriaService.listarTodos();
    }

    @GetMapping("/entidade")
    public List<LogAuditoria> buscarPorEntidade(@RequestParam String nome) {
        return logAuditoriaService.buscarPorEntidade(nome);
    }

    @GetMapping("/entidade/id")
    public List<LogAuditoria> buscarPorEntidadeEId(@RequestParam String entidade,
                                                   @RequestParam Integer id) {
        return logAuditoriaService.buscarPorEntidadeEId(entidade, id);
    }

    @GetMapping("/periodo")
    public List<LogAuditoria> buscarPorPeriodo(@RequestParam LocalDateTime inicio,
                                               @RequestParam LocalDateTime fim) {
        return logAuditoriaService.buscarPorPeriodo(inicio, fim);
    }
}
