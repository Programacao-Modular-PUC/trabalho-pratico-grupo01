package com.hospedagem.hospedagem.exceptions;

public class QuartoIndisponivelException extends RuntimeException {

    public QuartoIndisponivelException() {
        super("Quarto indisponível");
    }

    public QuartoIndisponivelException(String message) {
        super("Quarto indisponível: " + message);
    }
}
