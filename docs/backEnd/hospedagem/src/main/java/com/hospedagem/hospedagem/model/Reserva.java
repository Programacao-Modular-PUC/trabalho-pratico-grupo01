package com.hospedagem.hospedagem.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reserva {

    private int id;
    private Date dataEntrada;
    private Date dataSaida;
    private int qtdDiarias;
    private double valorFinal;
    private String status;

    private static List<Reserva> reservas = new ArrayList<>();

    public Reserva cadastrar(int clienteId, int quartoId, Date dataEntrada, Date dataSaida) {

        Reserva reserva = new Reserva();

        reserva.id = reservas.size() + 1;
        reserva.dataEntrada = dataEntrada;
        reserva.dataSaida = dataSaida;
        reserva.qtdDiarias = reserva.calcularDiarias();
        reserva.valorFinal = reserva.calcularValor();
        reserva.status = "ATIVA";

        reservas.add(reserva);
        return reserva;
    }

     public int calcularDiarias() {

        long diferenca = dataSaida.getTime() - dataEntrada.getTime();
        return (int) (diferenca / (1000 * 60 * 60 * 24));
    }

    public double calcularValor() {
        return qtdDiarias * 150;
    }

    public void atualizar(int id, Date dataEntrada, Date dataSaida, String status) {

        for (Reserva reserva : reservas) {
            if (reserva.id == id) {

                reserva.dataEntrada = dataEntrada;
                reserva.dataSaida = dataSaida;
                reserva.status = status;
                reserva.qtdDiarias = reserva.calcularDiarias();
                reserva.valorFinal = reserva.calcularValor();
                break;
            }
        }
    }

    public void cancelar(int id) {
        
        for (Reserva reserva : reservas) {
            if (reserva.id == id) {

                reserva.status = "CANCELADA";
                break;
            }
        }
    }
    
    //Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public int getQtdDiarias() {
        return qtdDiarias;
    }

    public void setQtdDiarias(int qtdDiarias) {
        this.qtdDiarias = qtdDiarias;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
