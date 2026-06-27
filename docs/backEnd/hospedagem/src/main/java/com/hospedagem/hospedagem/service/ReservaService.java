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

    public ReservaService() {
    }

    public List<Reserva> listarTodos() {
        return this.reservaRepository.findAll();
    }

    public List<Reserva> listarPorCliente(Integer clienteId) {
        return this.reservaRepository.findByClienteId(clienteId);
    }

    public Reserva buscar(Integer id) {
        return this.reservaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada"));
    }

    public Reserva cadastrar(ReservaRequestDTO dto) {
        this.validarDatas(dto.getDataEntrada(), dto.getDataSaida());

        Quarto quarto = this.quartoRepository.findById(dto.getQuartoId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quarto não encontrado"));

        if (quarto.getStatus() == StatusQuarto.INATIVO) {
            throw new QuartoIndisponivelException("Quarto inativo não pode ser utilizado");
        } else if (this.reservaRepository.existeConflitoDeDatas(dto.getQuartoId(), dto.getDataEntrada(), dto.getDataSaida())) {
            throw new QuartoIndisponivelException("Quarto indisponível para o período informado");
        }

        Cliente cliente = this.clienteRepository.findById(dto.getClienteId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        int diarias = (int) ChronoUnit.DAYS.between(dto.getDataEntrada(), dto.getDataSaida());

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setQuarto(quarto);
        reserva.setDataEntrada(dto.getDataEntrada());
        reserva.setDataSaida(dto.getDataSaida());
        reserva.setQtdDiarias(diarias);
        reserva.setStatus(StatusReserva.ATIVA);

        // --- INÍCIO DA INTEGRAÇÃO SPRINT 4 ---
        // 1. Verifica o histórico de hospedagens do cliente no banco
        int totalHospedagens = this.reservaRepository.findByClienteId(cliente.getId()).size();

        // 2. Busca as regras globais de pontuação via Singleton
        GerenciadorDeFidelidade gerenciador = GerenciadorDeFidelidade.getInstance();

        // 3. Define a categoria (Strategy) do cliente com base no histórico
        CategoriaFidelidade categoriaCliente;
        if (totalHospedagens >= gerenciador.getHospedagensParaOuro()) {
            categoriaCliente = new FidelidadeOuro();
        } else if (totalHospedagens >= gerenciador.getHospedagensParaPrata()) {
            categoriaCliente = new FidelidadePrata();
        } else {
            categoriaCliente = new FidelidadeBronze();
        }

        // 4. Calcula o valor base e aplica o desconto da estratégia correspondente
        double valorSemDesconto = quarto.calcularValor() * (double) diarias;
        double valorFinalComDesconto = categoriaCliente.calcularDesconto(valorSemDesconto);

        reserva.setValorFinal(valorFinalComDesconto);
        // --- FIM DA INTEGRAÇÃO SPRINT 4 ---

        Reserva salva = this.reservaRepository.save(reserva);
        this.logService.registrar("Reserva", salva.getId(), "CRIAR", "Reserva criada para cliente " + cliente.getNome() + " | Categoria: " + categoriaCliente.getClass().getSimpleName());
        return salva;
    }

    public Reserva atualizar(Integer id, ReservaRequestDTO dto) {
        Reserva existente = this.buscar(id);
        this.validarDatas(dto.getDataEntrada(), dto.getDataSaida());

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

        // --- INÍCIO DA INTEGRAÇÃO SPRINT 4 (Recálculo na Atualização) ---
        int totalHospedagens = this.reservaRepository.findByClienteId(existente.getCliente().getId()).size();
        GerenciadorDeFidelidade gerenciador = GerenciadorDeFidelidade.getInstance();

        CategoriaFidelidade categoriaCliente;
        if (totalHospedagens >= gerenciador.getHospedagensParaOuro()) {
            categoriaCliente = new FidelidadeOuro();
        } else if (totalHospedagens >= gerenciador.getHospedagensParaPrata()) {
            categoriaCliente = new FidelidadePrata();
        } else {
            categoriaCliente = new FidelidadeBronze();
        }

        double valorSemDesconto = existente.getQuarto().calcularValor() * (double) diarias;
        double valorFinalComDesconto = categoriaCliente.calcularDesconto(valorSemDesconto);

        existente.setValorFinal(valorFinalComDesconto);
        // --- FIM DA INTEGRAÇÃO SPRINT 4 ---

        this.logService.registrar("Reserva", id, "ATUALIZAR", "Reserva atualizada com recálculo de fidelidade");
        return this.reservaRepository.save(existente);
    }

    public void cancelar(Integer id) {
        Reserva reserva = this.buscar(id);
        reserva.setStatus(StatusReserva.CANCELADA);
        this.reservaRepository.save(reserva);
        this.logService.registrar("Reserva", id, "CANCELAR", "Reserva cancelada");
    }

    void validarDatas(LocalDate entrada, LocalDate saida) {
        if (entrada != null && saida != null) {
            if (entrada.isBefore(LocalDate.now())) {
                throw new DataInvalidaException("Data de entrada não pode ser anterior à data atual");
            } else if (!saida.isAfter(entrada)) {
                throw new DataInvalidaException("Data de saída deve ser posterior à data de entrada");
            }
        } else {
            throw new DataInvalidaException("Datas de entrada e saída não podem ser nulas");
        }
    }
}