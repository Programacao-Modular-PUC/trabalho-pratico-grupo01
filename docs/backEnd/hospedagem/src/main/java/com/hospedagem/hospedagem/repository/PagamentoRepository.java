package com.hospedagem.hospedagem.repository;

import com.hospedagem.hospedagem.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

    Optional<Pagamento> findByReservaId(Integer reservaId);
}