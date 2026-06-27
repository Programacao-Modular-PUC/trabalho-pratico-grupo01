package com.hospedagem.hospedagem.model;

public interface CategoriaFidelidade {
    // Método para aplicar os descontos progressivos
    double calcularDesconto(double valorOriginal);
    
    // Método para retornar a lista de benefícios exclusivos
    String obterBeneficios();
}