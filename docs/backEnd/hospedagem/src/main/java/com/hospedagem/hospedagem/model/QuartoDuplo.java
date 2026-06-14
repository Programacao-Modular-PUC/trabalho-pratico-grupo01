package com.hospedagem.hospedagem.model;

import com.hospedagem.hospedagem.exeptions.QuartoIndisponivelException;

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
    private double valorCamaKingQueen;
    private double valorCamaBerco;

    public QuartoDuplo() {
        super();
    }

    public QuartoDuplo(String tipo, double valorBase, boolean possuiAr, boolean possuiHidro, StatusQuarto status, TipoCama tipoCama, boolean berco, double valorCamaComum, double valorCamaKingQueen, double valorCamaBerco) {
        super();

        if (tipoCama == null) {
            throw new IllegalArgumentException("Tipo de cama não pode ser nulo");
        }

        if (valorCamaComum <= 0) {
            throw new IllegalArgumentException("Valor da cama comum deve ser maior que 0");
        }

        if (valorCamaKingQueen <= 0) {
            throw new IllegalArgumentException("Valor da cama King/Queen deve ser maior que 0");
        }

        if (valorCamaBerco <= 0) {
            throw new IllegalArgumentException("Valor do cama deve ser maior que 0");
        }

        if (status == StatusQuarto.INATIVO){
            throw new QuartoIndisponivelException("Quarto inativo não pode ser utilizado");
        }

        setTipo(tipo);
        setValorBase(valorBase);
        setPossuiAr(possuiAr);
        setPossuiHidro(possuiHidro);
        setStatus(status);
        this.tipoCama = tipoCama;
        this.berco = berco;
        this.valorCamaComum = valorCamaComum;
        this.valorCamaKingQueen = valorCamaKingQueen;
        this.valorCamaBerco = valorCamaBerco;
    }

    @Override
    public double calcularValor() {
        double valorTotal = getValorBase();

        if (tipoCama == TipoCama.COMUM) {
            valorTotal += valorCamaComum;
        } else if (tipoCama == TipoCama.QUEEN || tipoCama == TipoCama.KING) {
            valorTotal += valorCamaKingQueen;
        }

        if (berco) {
            valorTotal += valorCamaBerco;
        }

        return valorTotal;
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

    public double getValorCamaKingQueen() {
        return valorCamaKingQueen;
    }

    public void setValorCamaKingQueen(double valorCamaKingQueen) {
        this.valorCamaKingQueen = valorCamaKingQueen;
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

}
