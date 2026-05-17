package com.hospedagem.hospedagem.service;

import com.hospedagem.hospedagem.model.Quarto;
import com.hospedagem.hospedagem.model.StatusQuarto;
import com.hospedagem.hospedagem.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class QuartoService {

    @Autowired
    private QuartoRepository repository;

    public List<Quarto> listarTodos() { return repository.findAll(); }

    public List<Quarto> listarAtivos() {
        return repository.findByStatus(StatusQuarto.ATIVO);
    }

    public List<Quarto> listarDisponiveisPorPeriodo(LocalDate entrada, LocalDate saida) {
        return repository.findQuartosDisponiveisPorPeriodo(entrada, saida);
    }

    public Quarto buscar(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quarto não encontrado"));
    }

    public Quarto salvar(Quarto quarto) {
        return repository.save(quarto);
    }

    public Quarto atualizar(Integer id, Quarto dados) {
        Quarto existente = buscar(id);
        existente.setTipo(dados.getTipo());
        existente.setValorBase(dados.getValorBase());
        existente.setPossuiAr(dados.isPossuiAr());
        existente.setPossuiHidro(dados.isPossuiHidro());
        existente.setStatus(dados.getStatus());
        return repository.save(existente);
    }

    public void excluir(Integer id) {
        buscar(id);
        repository.deleteById(id);
    }

    public void alterarStatus(Integer id, StatusQuarto status) {
        Quarto quarto = buscar(id);
        quarto.setStatus(status);
        repository.save(quarto);
    }
}