package com.hospedagem.hospedagem.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hospedagem.hospedagem.model.QuartoIndividual;
import com.hospedagem.hospedagem.repository.QuartoIndividualRepository;

@Service
public class QuartoIndividualService {
    
    private QuartoIndividualRepository quartoIndividualRepository;

    public QuartoIndividualService(QuartoIndividualRepository quartoIndividualRepository){
        this.quartoIndividualRepository = quartoIndividualRepository;
    }

    public List<QuartoIndividual> listarTodos(){
        return quartoIndividualRepository.findAll();
    }

    public QuartoIndividual buscarPorId(Integer id){
        return quartoIndividualRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException
            (HttpStatus.NOT_FOUND, "Quarto Individual nao encontrado"));
    }

    public QuartoIndividual salvar(QuartoIndividual quartoIndividual){
        return quartoIndividualRepository.save(quartoIndividual);
    }

    public void excluir(Integer id){
        buscarPorId(id);
        quartoIndividualRepository.deleteById(id);
    }
}