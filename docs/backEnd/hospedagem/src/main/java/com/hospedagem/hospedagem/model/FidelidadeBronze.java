package com.hospedagem.hospedagem.model;

public class FidelidadeBronze implements CategoriaFidelidade {
    @Override
    public double calcularDesconto(double valorOriginal) {
        return valorOriginal; // Bronze não tem desconto
    }

    @Override
    public String obterBeneficios() {
        return "Nenhum benefício extra.";
    }
}