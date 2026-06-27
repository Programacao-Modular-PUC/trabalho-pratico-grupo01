package com.hospedagem.hospedagem.exceptions;

public class DataInvalidaException extends RuntimeException {

    public DataInvalidaException() {
        super("Data inválida");
    }

    public DataInvalidaException(String message) {
        super("Data inválida: " + message);
    }

}
