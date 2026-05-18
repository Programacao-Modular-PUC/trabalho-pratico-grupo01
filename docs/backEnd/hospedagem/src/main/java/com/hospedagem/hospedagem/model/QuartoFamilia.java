package com.hospedagem.hospedagem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "quartos_familia")
public class QuartoFamilia extends Quarto{
    
    private int capacidadeMaxima;
    private int quantidadeAmbientes;
    private TipoCama tipoCama;
    private int numeroDePessoas;
    private double valorPessoa;

    public QuartoFamilia(){
        super();
    }

    public QuartoFamilia(String tipo, double valorBase, boolean possuiAr, boolean possuiHidro, int capacidadeMaxima, int quantidadeAmbientes, TipoCama tipoCama, int numeroDePessoas, double valorPessoa){
        super();
        setTipo(tipo);
        setValorBase(valorBase);
        setPossuiAr(possuiAr);
        setPossuiHidro(possuiHidro);
        this.capacidadeMaxima = capacidadeMaxima;
        this.quantidadeAmbientes = quantidadeAmbientes;
        this.tipoCama = tipoCama;
        this.numeroDePessoas = numeroDePessoas;
        this.valorPessoa = valorPessoa;
    }

    public double calcularValor(){
        double valorTotal = getValorBase();

        valorTotal += (valorPessoa * numeroDePessoas);

        return valorTotal;
    }

    public int getCapacidadeMaxima() {
        return this.capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public int getQuantidadeAmbientes() {
        return this.quantidadeAmbientes;
    }

    public void setQuantidadeAmbientes(int quantidadeAmbientes) {
        this.quantidadeAmbientes = quantidadeAmbientes;
    }

    public TipoCama getTipoCama() {
        return this.tipoCama;
    }

    public void setTipoCama(TipoCama tipoCama) {
        this.tipoCama = tipoCama;
    }    

}
