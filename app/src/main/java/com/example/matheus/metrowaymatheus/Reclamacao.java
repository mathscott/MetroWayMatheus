package com.example.matheus.metrowaymatheus;

import java.util.Date;

public class Reclamacao {

    private String estacao;
    private String nome;
    private Date data;
    private String descricao;

    public Reclamacao() {
    }

    public Reclamacao(String estacao, String nome, Date data, String descricao) {
        this.estacao = estacao;
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
    }

    public String getEstacao() {
        return estacao;
    }

    public void setEstacao(String estacao) {
        this.estacao = estacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
