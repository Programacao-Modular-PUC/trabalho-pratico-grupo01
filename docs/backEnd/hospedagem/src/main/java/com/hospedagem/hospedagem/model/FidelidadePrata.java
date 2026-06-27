package com.hospedagem.hospedagem.model;

public class FidelidadePrata implements CategoriaFidelidade {
    @Override
    public double calcularDesconto(double valorOriginal) {
        return valorOriginal * 0.95; // 5% de desconto no aluguel
    }

    @Override
    public String obterBeneficios() {
        // Benefícios baseados nos exemplos da Sprint 4
        return "5% de desconto e Check-out estendido"; 
    }
}