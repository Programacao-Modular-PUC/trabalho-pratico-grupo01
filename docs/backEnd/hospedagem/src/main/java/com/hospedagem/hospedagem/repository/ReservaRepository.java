package com.hospedagem.hospedagem.repository;

import com.hospedagem.hospedagem.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    List<Reserva> findByClienteId(Integer clienteId);

    @Query("""
        SELECT COUNT(r) > 0 FROM Reserva r
        WHERE r.quarto.id = :quartoId
        AND r.status != 'CANCELADA'
        AND r.dataEntrada < :saida
        AND r.dataSaida > :entrada
    """)
    boolean existeConflitoDeDatas(
            @Param("quartoId") Integer quartoId,
            @Param("entrada") LocalDate entrada,
            @Param("saida") LocalDate saida
    );
}
