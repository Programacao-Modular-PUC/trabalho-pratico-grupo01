package com.hospedagem.hospedagem.service;

import com.hospedagem.hospedagem.model.Relatorio;
import com.hospedagem.hospedagem.repository.RelatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class RelatorioService {

    @Autowired
    private RelatorioRepository relatorioRepository;

    public List<Relatorio> listarTodos() {
        return relatorioRepository.findAll();
    }
    public Relatorio buscarPeloId(Integer id) {
        return relatorioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Relatório não encontrado"));
    }

    public Relatorio salvar(Relatorio relatorio) {
        return relatorioRepository.save(relatorio);
    }

    public Relatorio atualizar(Integer id, Relatorio relatorio) {
        Relatorio existente = buscarPeloId(id);
        existente.setTipo(relatorio.getTipo());
        existente.setArquivoUrl(relatorio.getArquivoUrl());
        return relatorioRepository.save(existente);

    }

    public void deletar(Integer id) {
        relatorioRepository.deleteById(id);
    }
}
