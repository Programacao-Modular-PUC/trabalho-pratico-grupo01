package com.hospedagem.hospedagem.model;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private Integer qtdDiarias;
    private Double valorFinal;

    @Enumerated(EnumType.STRING)
    private StatusReserva status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "quarto_id")
    private Quarto quarto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Integer getQtdDiarias() {
        return qtdDiarias;
    }

    public void setQtdDiarias(Integer qtdDiarias) {
        this.qtdDiarias = qtdDiarias;
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }
}