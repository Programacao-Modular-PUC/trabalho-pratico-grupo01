package com.hospedagem.hospedagem.exeptions;

public class QuartoIndisponiveExeption extends RuntimeException {

    public QuartoIndisponiveExeption() {
        super("Quarto indisponível");
    }

    public QuartoIndisponiveExeption(String message) {
        super("Quarto indisponível: " + message);
    }
}
