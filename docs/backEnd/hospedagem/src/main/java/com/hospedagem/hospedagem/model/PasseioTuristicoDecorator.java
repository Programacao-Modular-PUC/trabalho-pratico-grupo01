package com.hospedagem.hospedagem.model;

/**
 * Decorator para adicionar passeios turísticos à hospedagem.
 */
public class PasseioTuristicoDecorator extends ServicoDecorator {
    
    private static final double CUSTO_POR_PESSOA = 150.00;
    private int numeroPessoas;
    private String tipoPasseio;
    
    public PasseioTuristicoDecorator(Servico servicoDecorado, int numeroPessoas, String tipoPasseio) {
        super(servicoDecorado);
        this.numeroPessoas = numeroPessoas;
        this.tipoPasseio = tipoPasseio;
    }
    
    @Override
    public String getDescricao() {
        return servicoDecorado.getDescricao() + " + Passeio turístico: " + tipoPasseio + " para " + numeroPessoas + " pessoas";
    }
    
    @Override
    public double getCusto() {
        return servicoDecorado.getCusto() + (CUSTO_POR_PESSOA * numeroPessoas);
    }
    
    @Override
    public String getNome() {
        return servicoDecorado.getNome() + " com Passeio Turístico";
    }
    
    public int getNumeroPessoas() {
        return numeroPessoas;
    }
    
    public String getTipoPasseio() {
        return tipoPasseio;
    }
}
