package com.hospedagem.hospedagem.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Relatorio {

    private int id;
    private String tipo;
    private LocalDateTime dataGeracao;
    private String filtro;
    private String arquivoUrl;

     private static List<Relatorio> relatorios = new ArrayList<>();

    public Relatorio gerar(String tipo, String filtro) {

        Relatorio relatorio = new Relatorio();

        relatorio.id = relatorios.size() + 1;
        relatorio.tipo = tipo;
        relatorio.filtro = filtro;
        relatorio.dataGeracao = LocalDateTime.now();
        relatorio.arquivoUrl = "relatorio_" + relatorio.id + ".pdf";

        relatorios.add(relatorio);
        return relatorio;
    }

   public void baixar(int id) {

        for (Relatorio relatorio : relatorios) {
            if (relatorio.id == id) {

                System.out.println("Baixando: " + relatorio.arquivoUrl);
                break;
            }
        }
    }

   public List<Relatorio> listar() {
    return relatorios;
    }

    //Getters e setters
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

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public String getArquivoUrl() {
        return arquivoUrl;
    }

    public void setArquivoUrl(String arquivoUrl) {
        this.arquivoUrl = arquivoUrl;
    }
    
}
