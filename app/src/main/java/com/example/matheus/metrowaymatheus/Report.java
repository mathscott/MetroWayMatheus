package com.example.matheus.metrowaymatheus;

import java.util.Date;

public class Report {

    private boolean assedio;
    private boolean lentidao;
    private boolean lotacao;
    private boolean limpeza;
    private boolean arCondicionado;
    private boolean seguranca;

    public String getEstacao() {
        return estacao;
    }

    public void setEstacao(String estacao) {
        this.estacao = estacao;
    }

    private String estacao;

    public boolean isAssedio() {
        return assedio;
    }

    public void setAssedio(boolean assedio) {
        this.assedio = assedio;
    }

    public boolean isLentidao() {
        return lentidao;
    }

    public void setLentidao(boolean lentidao) {
        this.lentidao = lentidao;
    }

    public boolean isLotacao() {
        return lotacao;
    }

    public void setLotacao(boolean lotacao) {
        this.lotacao = lotacao;
    }

    public boolean isLimpeza() {
        return limpeza;
    }

    public void setLimpeza(boolean limpeza) {
        this.limpeza = limpeza;
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    public boolean isSeguranca() {
        return seguranca;
    }

    public void setSeguranca(boolean seguranca) {
        this.seguranca = seguranca;
    }




}
