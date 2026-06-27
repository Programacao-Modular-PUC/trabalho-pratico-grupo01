package com.hospedagem.hospedagem.DTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class ReservaRequestDTO {
    
    @NotNull // impede entrada de valores nulos
    private Integer clienteId;

    @NotNull // impede entrada de valores nulos
    private Integer quartoId;

    @NotNull // impede entrada de valores nulos
    @Future // impede entrada de datas passadas
    private LocalDate dataEntrada;

    @NotNull // impede entrada de valores nulos
    @Future // impede entrada de datas passadas
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
