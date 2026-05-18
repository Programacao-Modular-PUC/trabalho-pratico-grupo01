package com.hospedagem.hospedagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospedagem.hospedagem.model.QuartoFamilia;

@Repository
public interface QuartoFamiliaRepository extends JpaRepository<QuartoFamilia, Integer>{

    
}