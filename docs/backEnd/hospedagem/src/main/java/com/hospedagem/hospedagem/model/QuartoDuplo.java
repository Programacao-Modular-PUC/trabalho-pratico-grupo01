package com.hospedagem.hospedagem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "quartos_duplo")
public class QuartoDuplo extends Quarto{
    
    @Enumerated(EnumType.STRING)
    private TipoCama tipoCama;
    private boolean berco;
    private double valorCamaComum;
    private double valorCamaKing;
    private double valorCamaBerco;

    public QuartoDuplo() {
        super();
    }

    public QuartoDuplo(String tipo, double valorBase, boolean possuiAr, boolean possuiHidro, StatusQuarto status,
            TipoCama tipoCama, boolean berco, double valorCamaComum, double valorCamaKing,
            double valorCamaBerco) {
        super();
        setTipo(tipo);
        setValorBase(valorBase);
        setPossuiAr(possuiAr);
        setPossuiHidro(possuiHidro);
        setStatus(status);
        this.tipoCama = tipoCama;
        this.berco = berco;
        this.valorCamaComum = valorCamaComum;
        this.valorCamaKing = valorCamaKing;
        this.valorCamaBerco = valorCamaBerco;
    }

    public boolean isBerco() {
        return berco;
    }

    public void setBerco(boolean berco) {
        this.berco = berco;
    }

    public TipoCama getTipoCama() {
        return tipoCama;
    }

    public void setTipoCama(TipoCama tipoCama) {
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

    public void solicitarBerco() {
        this.berco = true;
    }

    public void cancelarBerco() {
        this.berco = false;
    }

    @Override
    public double calcularValor() {
        double valorTotal = getValorBase();

        if (tipoCama == TipoCama.COMUM) {
            valorTotal += valorCamaComum;
        } else if (tipoCama == TipoCama.QUEEN || tipoCama == TipoCama.KING) {
            valorTotal += valorCamaKing;
        }

        if (berco) {
            valorTotal += valorCamaBerco;
        }

        return valorTotal;
    }

}
