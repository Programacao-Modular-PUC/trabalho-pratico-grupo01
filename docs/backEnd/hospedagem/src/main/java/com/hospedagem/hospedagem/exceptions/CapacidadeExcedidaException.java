package com.hospedagem.hospedagem.exceptions;

public class CapacidadeExcedidaException extends RuntimeException {

    
    public CapacidadeExcedidaException() {
        super("Capacidade excedida");
    }

    public CapacidadeExcedidaException(String message) {
        super("Capacidade excedida: " + message);
    }
    
}
