package com.hospedagem.hospedagem.model;

import com.hospedagem.hospedagem.exeptions.QuartoIndisponiveExeption;

public class QuartoIndividual extends Quarto{
    
    private int numCamas;
    private double valorCama;

    public QuartoIndividual(){

    }

    public QuartoIndividual(String tipo, double valorBase, boolean possuiAr, boolean possuiHidro, StatusQuarto status, int numCamas, double valorCama){
        super();

        if (status == StatusQuarto.INATIVO){
            throw new QuartoIndisponiveExeption("Quarto inativo não pode ser utilizado");
        }

        if (valorCama <= 0) {
            throw new IllegalArgumentException("Valor da cama deve ser maior que 0");
        }

        setTipo(tipo);
        setValorBase(valorBase);
        setPossuiAr(possuiAr);
        setPossuiHidro(possuiHidro);
        setStatus(status);
        this.numCamas = numCamas;
        this.valorCama = valorCama;


    }

    @Override
    public double calcularValor(){
        
        double valorTotal = getValorBase();

        if (numCamas > 0){
            valorTotal += (valorCama * numCamas);
        }

        return valorTotal;

    }

    public int getNumCamas() {
        return numCamas;
    }

    public void setNumCamas(int numCamas) {
        this.numCamas = numCamas;
    }

    public double getValorCama() {
        return valorCama;
    }

    public void setValorCama(double valorCama) {
        this.valorCama = valorCama;
    }

}
