package com.hospedagem.hospedagem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_auditoria")
public class LogAuditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String entidade;
    private Integer entidadeId;
    private String acao;
    private LocalDateTime dataHora;
    private String usuario;
    private String detalhes;

    @PrePersist
    public void preencherDataHora() {
        this.dataHora = LocalDateTime.now();
    }
    //Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    public int getEntidadeId() {
        return entidadeId;
    }

    public void setEntidadeId(int entidadeId) {
        this.entidadeId = entidadeId;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
    
}
