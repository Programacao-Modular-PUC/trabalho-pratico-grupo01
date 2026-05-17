package com.hospedagem.hospedagem.DTO;

import java.time.LocalDate;

public class ReservaRequestDTO {
    private Integer clienteId;
    private Integer quartoId;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getQuartoId() {
        return quartoId;
    }

    public void setQuartoId(Integer quartoId) {
        this.quartoId = quartoId;
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
}
