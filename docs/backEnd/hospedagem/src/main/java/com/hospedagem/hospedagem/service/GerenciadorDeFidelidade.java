package com.hospedagem.hospedagem.service;

public class GerenciadorDeFidelidade {

    // 1. Instância estática e privada (exigência do Singleton)
    private static GerenciadorDeFidelidade instancia;

    // 2. Configurações globais do programa de fidelidade
    private int hospedagensParaPrata = 5;
    private int hospedagensParaOuro = 10;

    // 3. Construtor privado para evitar que outras classes deem "new"
    private GerenciadorDeFidelidade() {
    }

    // 4. Método público e estático para obter a única instância global do gerenciador
    public static GerenciadorDeFidelidade getInstance() {
        if (instancia == null) {
            instancia = new GerenciadorDeFidelidade();
        }
        return instancia;
    }

    // Métodos para acessar e modificar as regras de negócio
    public int getHospedagensParaPrata() {
        return hospedagensParaPrata;
    }

    public void setHospedagensParaPrata(int hospedagensParaPrata) {
        this.hospedagensParaPrata = hospedagensParaPrata;
    }

    public int getHospedagensParaOuro() {
        return hospedagensParaOuro;
    }

    public void setHospedagensParaOuro(int hospedagensParaOuro) {
        this.hospedagensParaOuro = hospedagensParaOuro;
    }
}
