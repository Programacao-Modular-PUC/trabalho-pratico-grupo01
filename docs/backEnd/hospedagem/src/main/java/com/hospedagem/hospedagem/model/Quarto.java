package com.hospedagem.hospedagem.model;

import java.util.ArrayList;
import java.util.List;

public class Quarto {

    private int id;
    private String tipo;
    private double valorBase;
    private boolean possuiAr;
    private boolean possuiHidro;
    private boolean ativo;

    private static List<Quarto> quartos = new ArrayList<>();

    public Quarto cadastrar(String tipo, double valorBase, boolean possuiAr, boolean possuiHidro) {

        Quarto quarto = new Quarto();

        quarto.id = quartos.size() + 1;
        quarto.tipo = tipo;
        quarto.valorBase = valorBase;
        quarto.possuiAr = possuiAr;
        quarto.possuiHidro = possuiHidro;
        quarto.ativo = true;

        quartos.add(quarto);
        return quarto;
    }

    public void atualizar(int id, String tipo, double valorBase, boolean possuiAr, boolean possuiHidro, boolean ativo) {

        for (Quarto quarto : quartos) {
            if (quarto.id == id) {

                quarto.tipo = tipo;
                quarto.valorBase = valorBase;
                quarto.possuiAr = possuiAr;
                quarto.possuiHidro = possuiHidro;
                quarto.ativo = ativo;
                break;
            }
        }
    }

     public void excluir(int id) {

        for (Quarto quarto : quartos) {
            if (quarto.id == id) {

                quartos.remove(quarto);
                break;
            }
        }
    }

    public double calcularValor() {

        double valorFinal = valorBase;

        if (possuiAr) {
            valorFinal += 50;
        }

        if (possuiHidro) {
            valorFinal += 100;
        }
        return valorFinal;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public boolean isPossuiAr() {
        return possuiAr;
    }

    public void setPossuiAr(boolean possuiAr) {
        this.possuiAr = possuiAr;
    }

    public boolean isPossuiHidro() {
        return possuiHidro;
    }

    public void setPossuiHidro(boolean possuiHidro) {
        this.possuiHidro = possuiHidro;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
