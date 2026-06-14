package com.hospedagem.hospedagem.model;

import com.hospedagem.hospedagem.exeptions.CapacidadeExcedidaException;
import com.hospedagem.hospedagem.exeptions.QuartoIndisponiveExeption;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "quartos_familia")
public class QuartoFamilia extends Quarto{
    
    private int capacidadeMaxima;
    private int quantidadeAmbientes;
    @Enumerated(EnumType.STRING)
    private TipoCama tipoCama;
    private int numeroDePessoas;
    private double valorPessoa;

    public QuartoFamilia(){
        super();
    }

    public QuartoFamilia(String tipo, double valorBase, boolean possuiAr, boolean possuiHidro, StatusQuarto status, int capacidadeMaxima, int quantidadeAmbientes, TipoCama tipoCama, int numeroDePessoas, double valorPessoa){
        super();
        
        if (numeroDePessoas > capacidadeMaxima){
            throw new CapacidadeExcedidaException("Número de pessoas (" + numeroDePessoas + ") excede a capacidade máxima (" + capacidadeMaxima + ")");
        }

        if (tipoCama == null) {
            throw new IllegalArgumentException("Tipo de cama não pode ser nulo");
        }

        if (valorPessoa <= 0) {
            throw new IllegalArgumentException("Valor da pessoa deve ser maior que 0");
        }

        if (status == StatusQuarto.INATIVO){
            throw new QuartoIndisponiveExeption("Quarto inativo não pode ser utilizado");
        }

        setTipo(tipo);
        setValorBase(valorBase);
        setPossuiAr(possuiAr);
        setPossuiHidro(possuiHidro);
        setStatus(status);
        this.capacidadeMaxima = capacidadeMaxima;
        this.quantidadeAmbientes = quantidadeAmbientes;
        this.tipoCama = tipoCama;
        this.numeroDePessoas = numeroDePessoas;
        this.valorPessoa = valorPessoa;
    }

    @Override
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
        if (tipoCama == null) {
            throw new IllegalArgumentException("Tipo de cama não pode ser nulo");
        }
        this.tipoCama = tipoCama;
    }

    public int getNumeroDePessoas() {
        return this.numeroDePessoas;
    }

    public void setNumeroDePessoas(int numeroDePessoas) {
        if (numeroDePessoas > this.capacidadeMaxima) {
            throw new CapacidadeExcedidaException("Número de pessoas (" + numeroDePessoas + ") excede a capacidade máxima (" + this.capacidadeMaxima + ")");
        }
        this.numeroDePessoas = numeroDePessoas;
    }

    public double getValorPessoa() {
        return this.valorPessoa;
    }

    public void setValorPessoa(double valorPessoa) {
        if (valorPessoa <= 0) {
            throw new IllegalArgumentException("Valor da pessoa deve ser maior que 0");
        }
        this.valorPessoa = valorPessoa;
    }

    @Override
    public void setStatus(StatusQuarto status) {
        if (status == StatusQuarto.INATIVO){
            throw new QuartoIndisponiveExeption("Quarto inativo não pode ser utilizado");
        }
        super.setStatus(status);
    }
}
