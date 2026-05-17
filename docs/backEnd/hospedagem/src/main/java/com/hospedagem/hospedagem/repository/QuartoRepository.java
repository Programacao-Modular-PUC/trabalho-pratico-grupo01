package com.hospedagem.hospedagem.repository;

import com.hospedagem.hospedagem.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import com.hospedagem.hospedagem.model.StatusQuarto;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Integer> {

    // busca quartos com status ATIVO
    List<Quarto> findByStatus(StatusQuarto status);

    List<Quarto> findByTipo(String tipo);

    @Query("""
        SELECT q FROM Quarto q
        WHERE q.status = 'ATIVO'
        AND q.id NOT IN (
            SELECT r.quarto.id FROM Reserva r
            WHERE r.status != 'CANCELADA'
            AND r.dataEntrada < :saida
            AND r.dataSaida > :entrada
        )
    """)
    List<Quarto> findQuartosDisponiveisPorPeriodo(
            @Param("entrada") LocalDate entrada,
            @Param("saida") LocalDate saida
    );
}

