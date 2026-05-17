package com.hospedagem.hospedagem.service;

import com.hospedagem.hospedagem.model.Pagamento;
import com.hospedagem.hospedagem.model.Reserva;
import com.hospedagem.hospedagem.model.StatusPagamento;
import com.hospedagem.hospedagem.repository.PagamentoRepository;
import com.hospedagem.hospedagem.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private LogAuditoriaService logService;


    public Pagamento registrar(Integer reservaId, Double valor, String formaPagamento) {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada"));

        if (pagamentoRepository.findByReservaId(reservaId).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Reserva já possui pagamento registrado");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setReserva(reserva);
        pagamento.setValor(valor);
        pagamento.setFormaPagamento(formaPagamento);
        pagamento.setStatus(StatusPagamento.PENDENTE); // direto no enum
        pagamento.setDataPagamento(LocalDate.now());

        Pagamento salvo = pagamentoRepository.save(pagamento);
        logService.registrar("Pagamento", salvo.getId(), "CRIAR", "Pagamento registrado");
        return salvo;
    }

    public Pagamento atualizarStatus(Integer id, StatusPagamento novoStatus) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado"));

        pagamento.setStatus(novoStatus);
        logService.registrar("Pagamento", id, "ATUALIZAR", "Status atualizado para " + novoStatus);
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento consultar(Integer id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado"));
    }
}