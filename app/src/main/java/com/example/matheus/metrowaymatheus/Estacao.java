package com.example.matheus.metrowaymatheus;

import com.google.android.gms.maps.model.LatLng;

public class Estacao {

    private String linha;

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private String nome;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    private double latitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    private double longitude;

    public LatLng getPosicao() {
        return posicao;
    }

    private LatLng posicao;

    public Estacao(String nome, double latitude, double longitude){
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.posicao = new LatLng(latitude, longitude);
    }
}

