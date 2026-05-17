package com.hospedagem.hospedagem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "relatorios")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipo;
    private LocalDateTime dataGeracao;
    private String filtro;
    private String arquivoUrl;

    @PrePersist
    public void preencherDataGeracao() {
        this.dataGeracao = LocalDateTime.now();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public LocalDateTime getDataGeracao() { return dataGeracao; }
    public void setDataGeracao(LocalDateTime dataGeracao) { this.dataGeracao = dataGeracao; }
    public String getFiltro() { return filtro; }
    public void setFiltro(String filtro) { this.filtro = filtro; }
    public String getArquivoUrl() { return arquivoUrl; }
    public void setArquivoUrl(String arquivoUrl) { this.arquivoUrl = arquivoUrl; }
}