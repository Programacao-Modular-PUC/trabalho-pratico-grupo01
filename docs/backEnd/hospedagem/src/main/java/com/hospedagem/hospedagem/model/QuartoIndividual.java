package com.hospedagem.hospedagem.model;

public class QuartoIndividual extends Quarto{
    
    private int numCamas;
    private double valorCama;

    public QuartoIndividual(){

    }

    public QuartoIndividual(String tipo, double valorBase, boolean possuiAr, boolean possuiHidro, int numCamas, double valorCama){
        super();
        setTipo(tipo);
        setValorBase(valorBase);
        setPossuiAr(possuiAr);
        setPossuiHidro(possuiHidro);

        this.numCamas = numCamas;
        this.valorCama = valorCama;
    }

    public double CalcularValor(){
        
        double valorTotal = 0, valorBase = getValorBase();

        if (numCamas == 1){
            valorTotal = valorBase;
        }else if(numCamas > 1){
            valorTotal = (valorBase + (valorCama * numCamas));
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
