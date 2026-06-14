package com.hospedagem.hospedagem.exeptions;

public class QuartoIndisponivelException extends RuntimeException {

    public QuartoIndisponivelException() {
        super("Quarto indisponível");
    }

    public QuartoIndisponivelException(String message) {
        super("Quarto indisponível: " + message);
    }
}
