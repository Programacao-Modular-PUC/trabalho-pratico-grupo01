package com.hospedagem.hospedagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospedagem.hospedagem.model.QuartoIndividual;

@Repository
public interface QuartoIndividualRepository extends JpaRepository<QuartoIndividual, Integer>{

    
}