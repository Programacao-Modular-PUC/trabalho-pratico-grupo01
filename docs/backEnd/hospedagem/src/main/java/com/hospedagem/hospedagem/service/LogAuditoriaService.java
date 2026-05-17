package com.hospedagem.hospedagem.service;

import com.hospedagem.hospedagem.model.LogAuditoria;
import com.hospedagem.hospedagem.repository.LogAuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogAuditoriaService {

    @Autowired
    private LogAuditoriaRepository repository;

    // método utilitário chamado pelos outros services

    public void registrar(String entidade, Integer entidadeId, String acao, String detalhes) {
        LogAuditoria log = new LogAuditoria();
        log.setEntidade(entidade);
        log.setEntidadeId(entidadeId);
        log.setAcao(acao);
        log.setDetalhes(detalhes);
        log.setUsuario("sistema"); // futuramente vem do token JWT
        repository.save(log);
    }

    public List<LogAuditoria> listarTodos() {
        return repository.findAll();
    }

    public List<LogAuditoria> buscarPorEntidade(String entidade) {
        return repository.findByEntidade(entidade);
    }

    public List<LogAuditoria> buscarPorEntidadeEId(String entidade, Integer entidadeId) {
        return repository.findByEntidadeAndEntidadeId(entidade, entidadeId);
    }

    public List<LogAuditoria> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return repository.findByPeriodo(inicio, fim);
    }
}
