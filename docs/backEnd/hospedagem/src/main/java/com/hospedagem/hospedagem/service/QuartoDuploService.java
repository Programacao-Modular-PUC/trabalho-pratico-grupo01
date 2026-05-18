package com.hospedagem.hospedagem.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hospedagem.hospedagem.model.QuartoDuplo;
import com.hospedagem.hospedagem.repository.QuartoDuploRepository;

@Service
public class QuartoDuploService {

    private final QuartoDuploRepository quartoDuploRepository;

    public QuartoDuploService(QuartoDuploRepository quartoDuploRepository) {
        this.quartoDuploRepository = quartoDuploRepository;
    }

    public List<QuartoDuplo> listarTodos() {
        return quartoDuploRepository.findAll();
    }

    public QuartoDuplo buscarPorId(Integer id) {
        return quartoDuploRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quarto duplo nao encontrado"));
    }

    public QuartoDuplo salvar(QuartoDuplo quartoDuplo) {
        return quartoDuploRepository.save(quartoDuplo);
    }

    public void excluir(Integer id) {
        buscarPorId(id);
        quartoDuploRepository.deleteById(id);
    }
}
