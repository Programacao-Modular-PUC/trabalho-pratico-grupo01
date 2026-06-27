package com.hospedagem.hospedagem.model;

/**
 * Componente concreto base para o padrão Decorator.
 * Representa a hospedagem básica sem serviços adicionais.
 */
public class HospedagemBase implements Servico {
    
    private Quarto quarto;
    private int numeroDiarias;
    
    public HospedagemBase(Quarto quarto, int numeroDiarias) {
        this.quarto = quarto;
        this.numeroDiarias = numeroDiarias;
    }
    
    @Override
    public String getDescricao() {
        return "Hospedagem básica - " + quarto.getTipo() + 
               " (" + numeroDiarias + " diárias)";
    }
    
    @Override
    public double getCusto() {
        return quarto.calcularValor() * numeroDiarias;
    }
    
    @Override
    public String getNome() {
        return "Hospedagem Básica";
    }
    
    public Quarto getQuarto() {
        return quarto;
    }
    
    public int getNumeroDiarias() {
        return numeroDiarias;
    }
}
