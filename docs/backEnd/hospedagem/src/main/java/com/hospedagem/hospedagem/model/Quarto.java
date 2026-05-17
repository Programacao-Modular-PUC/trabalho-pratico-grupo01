package com.hospedagem.hospedagem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "quartos")
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipo;
    private double valorBase;
    private boolean possuiAr;
    private boolean possuiHidro;

    @Enumerated(EnumType.STRING)
    private StatusQuarto status;

    public double calcularValor() {
        return valorBase;
    }

    // getters e setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public double getValorBase() { return valorBase; }
    public void setValorBase(double valorBase) { this.valorBase = valorBase; }
    public boolean isPossuiAr() { return possuiAr; }
    public void setPossuiAr(boolean possuiAr) { this.possuiAr = possuiAr; }
    public boolean isPossuiHidro() { return possuiHidro; }
    public void setPossuiHidro(boolean possuiHidro) { this.possuiHidro = possuiHidro; }
    public StatusQuarto getStatus() { return status; }
    public void setStatus(StatusQuarto status) { this.status = status; }
}