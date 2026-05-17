package com.hospedagem.hospedagem.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double valor;
    private String formaPagamento;
    private LocalDate dataPagamento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @OneToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }

    public LocalDate getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }

    public StatusPagamento getStatus() { return status; }
    public void setStatus(StatusPagamento status) { this.status = status; }

    public Reserva getReserva() { return reserva; }
    public void setReserva(Reserva reserva) { this.reserva = reserva; }
}