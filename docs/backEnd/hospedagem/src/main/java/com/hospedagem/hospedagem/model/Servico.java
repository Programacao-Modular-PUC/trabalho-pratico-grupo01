package com.hospedagem.hospedagem.model;

/**
 * Interface base para o padrão Decorator.
 * Define o contrato para todos os serviços de hospedagem.
 */
public interface Servico {
    
    /**
     * Retorna a descrição do serviço/pacote
     */
    String getDescricao();
    
    /**
     * Retorna o custo total do serviço/pacote
     */
    double getCusto();
    
    /**
     * Retorna o nome do serviço
     */
    String getNome();
}
