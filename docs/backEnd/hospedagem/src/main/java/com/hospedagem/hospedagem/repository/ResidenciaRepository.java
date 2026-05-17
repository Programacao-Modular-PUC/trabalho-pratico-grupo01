package com.hospedagem.hospedagem.repository;

import com.hospedagem.hospedagem.model.Residencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResidenciaRepository extends JpaRepository<Residencia, Integer> {
}
