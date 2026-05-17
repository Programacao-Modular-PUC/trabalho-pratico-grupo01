package com.hospedagem.hospedagem.repository;

import com.hospedagem.hospedagem.model.LogAuditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogAuditoriaRepository extends JpaRepository<LogAuditoria, Integer>{

        List<LogAuditoria> findByEntidade(String entidade);
        List<LogAuditoria> findByEntidadeAndEntidadeId(String entidade, Integer entidadeId);

        @Query("""
        SELECT l FROM LogAuditoria l
        WHERE l.dataHora BETWEEN :inicio AND :fim
        ORDER BY l.dataHora DESC
    """)
        List<LogAuditoria> findByPeriodo(
                @Param("inicio") LocalDateTime inicio,
                @Param("fim") LocalDateTime fim
        );
}
