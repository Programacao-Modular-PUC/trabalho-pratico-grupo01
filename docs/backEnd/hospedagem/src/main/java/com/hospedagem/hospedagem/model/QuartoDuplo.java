package com.hospedagem.hospedagem.model;

public class QuartoDuplo extends Quarto{
    
    private String tipoCama;
    private boolean berco;
    private double valorCamaComum;
    private double valorCamaKing;
    private double valorCamaBerco;


    public boolean isBerco() {
        return berco;
    }

    public void setBerco(boolean berco) {
        this.berco = berco;
    }

    public String getTipoCama() {
        return tipoCama;
    }

    public void setTipoCama(String tipoCama) {
        this.tipoCama = tipoCama;
    }

    public double getValorCamaComum() {
        return valorCamaComum;
    }

    public void setValorCamaComum(double valorCamaComum) {
        this.valorCamaComum = valorCamaComum;
    }

    public double getValorCamaKing() {
        return valorCamaKing;
    }

    public void setValorCamaKing(double valorCamaKing) {
        this.valorCamaKing = valorCamaKing;
    }
    
    public double getValorCamaBerco() {
        return valorCamaBerco;
    }

    public void setValorCamaBerco(double valorCamaBerco) {
        this.valorCamaBerco = valorCamaBerco;
    }

}
