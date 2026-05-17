package com.hospedagem.hospedagem.service;

import com.hospedagem.hospedagem.model.Residencia;
import com.hospedagem.hospedagem.repository.ResidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ResidenciaService {
    @Autowired
    private ResidenciaRepository residenciaRepository;

    public List<Residencia> listarTodos() {
        return residenciaRepository.findAll();
    }

    public Residencia buscar(Integer id) {
        return residenciaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Residência não encontrada"));
    }

    public Residencia salvar(Residencia residencia) {
        return residenciaRepository.save(residencia);
    }

    public Residencia atualizar(Integer id, Residencia dados) {
        Residencia existente = buscar(id);
        existente.setEndereco(dados.getEndereco());
        existente.setNumero(dados.getNumero());
        existente.setBairro(dados.getBairro());
        existente.setCep(dados.getCep());
        existente.setTelefone(dados.getTelefone());
        existente.setEmail(dados.getEmail());
        return residenciaRepository.save(existente);
    }

    public void excluir(Integer id) {
        buscar(id);
        residenciaRepository.deleteById(id);
    }


}
