package com.hospedagem.hospedagem.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Pagamento {

    private int id;
    private double valor;
    private StatusPagamento status;
    private String formaPagamento;
    private Date dataPagamento;

    private static List<Pagamento> pagamentos = new ArrayList<>();

    public Pagamento registrar(int reservaId, double valor, String formaPagamento) {

        Pagamento pagamento = new Pagamento();

        pagamento.id = pagamentos.size() + 1;
        pagamento.valor = valor;
        pagamento.formaPagamento = formaPagamento;
        pagamento.status = StatusPagamento.PENDENTE;
        pagamento.dataPagamento = new Date();

        pagamentos.add(pagamento);

        return pagamento;
    }

       public void atualizarStatus(int id, String status) {

        for (Pagamento pagamento : pagamentos) {

            if (pagamento.id == id) {

                pagamento.status = StatusPagamento.valueOf(status);

                break;
            }
        }
    }

     public Pagamento consultar(int id) {

        for (Pagamento pagamento : pagamentos) {

            if (pagamento.id == id) {

                return pagamento;
            }
        }

        return null;
    }

    //Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    
}
