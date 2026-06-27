package com.hospedagem.hospedagem.model;

/**
 * Decorator para adicionar serviço de café da manhã à hospedagem.
 */
public class CafeDaManhaDecorator extends ServicoDecorator {
    
    private static final double CUSTO_POR_PESSOA = 35.00;
    private int numeroPessoas;
    
    public CafeDaManhaDecorator(Servico servicoDecorado, int numeroPessoas) {
        super(servicoDecorado);
        this.numeroPessoas = numeroPessoas;
    }
    
    @Override
    public String getDescricao() {
        return servicoDecorado.getDescricao() + " + Café da manhã para " + numeroPessoas + " pessoas";
    }
    
    @Override
    public double getCusto() {
        return servicoDecorado.getCusto() + (CUSTO_POR_PESSOA * numeroPessoas);
    }
    
    @Override
    public String getNome() {
        return servicoDecorado.getNome() + " com Café da Manhã";
    }
    
    public int getNumeroPessoas() {
        return numeroPessoas;
    }
}
