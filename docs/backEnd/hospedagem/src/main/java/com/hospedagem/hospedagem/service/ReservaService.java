package com.hospedagem.hospedagem.service;

import com.hospedagem.hospedagem.DTO.ReservaRequestDTO;
import com.hospedagem.hospedagem.exceptions.DataInvalidaException;
import com.hospedagem.hospedagem.exceptions.QuartoIndisponivelException;
import com.hospedagem.hospedagem.model.*;
import com.hospedagem.hospedagem.repository.ClienteRepository;
import com.hospedagem.hospedagem.repository.QuartoRepository;
import com.hospedagem.hospedagem.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LogAuditoriaService logService;

    public List<Reserva> listarTodos() {
        return reservaRepository.findAll();
    }

    public List<Reserva> listarPorCliente(Integer clienteId) {
        return reservaRepository.findByClienteId(clienteId);
    }

    public Reserva buscar(Integer id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada"));
    }

    public Reserva cadastrar(ReservaRequestDTO dto) {
        validarDatas(dto.getDataEntrada(), dto.getDataSaida());

        Quarto quarto = quartoRepository.findById(dto.getQuartoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quarto não encontrado"));

        if (quarto.getStatus() == StatusQuarto.INATIVO ) {
            throw new QuartoIndisponivelException("Quarto inativo não pode ser utilizado");
        }

        if (reservaRepository.existeConflitoDeDatas(dto.getQuartoId(), dto.getDataEntrada(), dto.getDataSaida())) {
            throw new QuartoIndisponivelException("Quarto indisponível para o período informado");
        }

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        int diarias = (int) ChronoUnit.DAYS.between(dto.getDataEntrada(), dto.getDataSaida());

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setQuarto(quarto);
        reserva.setDataEntrada(dto.getDataEntrada());
        reserva.setDataSaida(dto.getDataSaida());
        reserva.setQtdDiarias(diarias);
        reserva.setValorFinal(quarto.getValorBase() * diarias);
        reserva.setStatus(StatusReserva.ATIVA);

        Reserva salva = reservaRepository.save(reserva);

        logService.registrar("Reserva", salva.getId(), "CRIAR",
                "Reserva criada para cliente " + cliente.getNome());

        return salva;
    }

    public Reserva atualizar(Integer id, ReservaRequestDTO dto) {
        Reserva existente = buscar(id);

        validarDatas(dto.getDataEntrada(), dto.getDataSaida());

        Quarto quarto = existente.getQuarto();

        if (quarto.getStatus() == StatusQuarto.INATIVO) {
            throw new QuartoIndisponivelException("Quarto inativo não pode ser utilizado");
        }

        // Verifica conflito com outras reservas (excluindo a reserva atual)
        if (reservaRepository.existeConflitoDeDatasExcluindoId(dto.getQuartoId(), dto.getDataEntrada(), dto.getDataSaida(), id)) {
            throw new QuartoIndisponivelException("Quarto indisponível para o período informado");
        }

        int diarias = (int) ChronoUnit.DAYS.between(dto.getDataEntrada(), dto.getDataSaida());

        existente.setDataEntrada(dto.getDataEntrada());
        existente.setDataSaida(dto.getDataSaida());
        existente.setQtdDiarias(diarias);
        existente.setValorFinal(existente.getQuarto().getValorBase() * diarias);

        logService.registrar("Reserva", id, "ATUALIZAR", "Reserva atualizada");

        return reservaRepository.save(existente);
    }

    public void cancelar(Integer id) {
        Reserva reserva = buscar(id);
        reserva.setStatus(StatusReserva.CANCELADA);
        reservaRepository.save(reserva);

        logService.registrar("Reserva", id, "CANCELAR", "Reserva cancelada");
    }

    void validarDatas(LocalDate entrada, LocalDate saida) {
        if (entrada == null || saida == null) {
            throw new DataInvalidaException("Datas de entrada e saída não podem ser nulas");
        }
        
        if (entrada.isBefore(LocalDate.now())) {
            throw new DataInvalidaException("Data de entrada não pode ser anterior à data atual");
        }
        
        if (!saida.isAfter(entrada)) {
            throw new DataInvalidaException("Data de saída deve ser posterior à data de entrada");
        }
    }
}