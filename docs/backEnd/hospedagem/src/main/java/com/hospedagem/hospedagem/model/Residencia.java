package com.hospedagem.hospedagem.model;

import java.util.ArrayList;
import java.util.List;

public class Residencia {

    private int id;
    private String endereco;
    private String numero;
    private String bairro;
    private String cep;
    private String telefone;
    private String email;

    private static List<Residencia> residencias = new ArrayList<>();

    public Residencia cadastrar(String endereco, String numero, String bairro, String cep, String telefone, String email) {
        Residencia residencia = new Residencia();

        residencia.id = residencias.size() + 1;
        residencia.endereco = endereco;
        residencia.numero = numero;
        residencia.bairro = bairro;
        residencia.cep = cep;
        residencia.telefone = telefone;
        residencia.email = email;

        residencias.add(residencia);
        return residencia;
    }

    public void atualizar(int id, String endereco, String numero, String bairro, String cep, String telefone, String email) {

        for (Residencia residencia : residencias) {
            if (residencia.id == id) {

                residencia.endereco = endereco;
                residencia.numero = numero;
                residencia.bairro = bairro;
                residencia.cep = cep;
                residencia.telefone = telefone;
                residencia.email = email;

                break;
            }
        }
    }

    public void excluir(int id) {

        for (Residencia residencia : residencias) {
            if (residencia.id == id) {

                residencias.remove(residencia);
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}


