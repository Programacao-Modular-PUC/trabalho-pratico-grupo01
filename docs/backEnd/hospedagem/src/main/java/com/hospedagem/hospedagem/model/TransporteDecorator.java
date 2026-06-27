package com.hospedagem.hospedagem.model;

/**
 * Decorator para adicionar serviço de transporte à hospedagem.
 */
public class TransporteDecorator extends ServicoDecorator {
    
    private static final double CUSTO_DIARIO = 200.00;
    private int numeroDias;
    
    public TransporteDecorator(Servico servicoDecorado, int numeroDias) {
        super(servicoDecorado);
        this.numeroDias = numeroDias;
    }
    
    @Override
    public String getDescricao() {
        return servicoDecorado.getDescricao() + " + Transporte por " + numeroDias + " dias";
    }
    
    @Override
    public double getCusto() {
        return servicoDecorado.getCusto() + (CUSTO_DIARIO * numeroDias);
    }
    
    @Override
    public String getNome() {
        return servicoDecorado.getNome() + " com Transporte";
    }
    
    public int getNumeroDias() {
        return numeroDias;
    }
}
