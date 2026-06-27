package com.hospedagem.hospedagem.model;

public class FidelidadeOuro implements CategoriaFidelidade {
    @Override
    public double calcularDesconto(double valorOriginal) {
        return valorOriginal * 0.90; // 10% de desconto no aluguel
    }

    @Override
    public String obterBeneficios() {
        // Benefícios baseados nos exemplos da Sprint 4
        return "10% de desconto, Check-out estendido, Upgrade de quarto e Diárias gratuitas";
    }
}