package com.hospedagem.hospedagem.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogAuditoria {

    private int id;
    private String entidade;
    private int entidadeId;
    private String acao;
    private LocalDateTime dataHora;
    private String usuario;
    private String detalhes;

     private static List<LogAuditoria> logs = new ArrayList<>();

    public void registrar(String entidade, int entidadeId, String acao, String usuario, String detalhes) {

        LogAuditoria log = new LogAuditoria();

        log.id = logs.size() + 1;
        log.entidade = entidade;
        log.entidadeId = entidadeId;
        log.acao = acao;
        log.usuario = usuario;
        log.detalhes = detalhes;
        log.dataHora = LocalDateTime.now();

        logs.add(log);
    }

    public List<LogAuditoria> consultar(String filtro) {

        List<LogAuditoria> resultado = new ArrayList<>();
        
         for (LogAuditoria log : logs) {
            if (log.entidade.contains(filtro)
                    || log.acao.contains(filtro)
                    || log.usuario.contains(filtro)
                    || log.detalhes.contains(filtro)) {

                resultado.add(log);
            }
         }
        return resultado;
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
