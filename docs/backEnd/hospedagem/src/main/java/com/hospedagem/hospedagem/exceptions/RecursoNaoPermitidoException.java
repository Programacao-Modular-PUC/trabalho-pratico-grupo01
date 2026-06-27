package com.hospedagem.hospedagem.exceptions;

public class RecursoNaoPermitidoException extends RuntimeException {

    public RecursoNaoPermitidoException() {
        super("Recurso não permitido");
    }

    public RecursoNaoPermitidoException(String message) {
        super("Recurso não permitido: " + message);
    }
}
