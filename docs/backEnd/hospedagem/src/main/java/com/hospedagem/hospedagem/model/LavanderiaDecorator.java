package com.hospedagem.hospedagem.model;

/**
 * Decorator para adicionar serviço de lavanderia à hospedagem.
 */
public class LavanderiaDecorator extends ServicoDecorator {
    
    private static final double CUSTO_POR_KG = 15.00;
    private double quilos;
    
    public LavanderiaDecorator(Servico servicoDecorado, double quilos) {
        super(servicoDecorado);
        this.quilos = quilos;
    }
    
    @Override
    public String getDescricao() {
        return servicoDecorado.getDescricao() + " + Lavanderia (" + quilos + " kg)";
    }
    
    @Override
    public double getCusto() {
        return servicoDecorado.getCusto() + (CUSTO_POR_KG * quilos);
    }
    
    @Override
    public String getNome() {
        return servicoDecorado.getNome() + " com Lavanderia";
    }
    
    public double getQuilos() {
        return quilos;
    }
}
