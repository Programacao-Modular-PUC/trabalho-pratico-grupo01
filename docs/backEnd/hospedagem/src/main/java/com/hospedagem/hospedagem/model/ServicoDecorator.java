package com.hospedagem.hospedagem.model;

/**
 * Classe abstrata base para todos os decoradores de serviço.
 * Implementa o padrão Decorator para adicionar funcionalidades
 * dinamicamente à hospedagem base.
 */
public abstract class ServicoDecorator implements Servico {
    
    protected Servico servicoDecorado;
    
    public ServicoDecorator(Servico servicoDecorado) {
        this.servicoDecorado = servicoDecorado;
    }
    
    @Override
    public String getDescricao() {
        return servicoDecorado.getDescricao();
    }
    
    @Override
    public double getCusto() {
        return servicoDecorado.getCusto();
    }
    
    @Override
    public String getNome() {
        return servicoDecorado.getNome();
    }
    
    protected Servico getServicoDecorado() {
        return servicoDecorado;
    }
}
