package com.hospedagem.hospedagem.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.hospedagem.hospedagem.model.QuartoFamilia;
import com.hospedagem.hospedagem.repository.QuartoFamiliaRepository;

public class QuartoFamiliaService {
    
    private QuartoFamiliaRepository quartoFamiliaRepository;

    public QuartoFamiliaService(QuartoFamiliaRepository quartoFamiliaRepository){
        this.quartoFamiliaRepository = quartoFamiliaRepository;
    }

    public List<QuartoFamilia> listarTodos(){
        return quartoFamiliaRepository.findAll();
    }

    public QuartoFamilia buscarPorId(Integer id){
        return quartoFamiliaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException
            (HttpStatus.NOT_FOUND, "Quarto familia nao encontrado"));
    }

    public QuartoFamilia salvar(QuartoFamilia quartoFamilia){
        return quartoFamiliaRepository.save(quartoFamilia);
    }

    public void excluir(Integer id){
        buscarPorId(id);
        quartoFamiliaRepository.deleteById(id);
    }
}
