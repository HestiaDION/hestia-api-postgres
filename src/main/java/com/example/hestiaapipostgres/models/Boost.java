package com.example.hestiaapipostgres.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Boost {
    @Id
    private UUID id;
    private String info;
    private String nome_boost;
    private double valor;
    private double perc_boost;

    public Boost(){}

    public Boost(UUID id, String info, String nome_boost, double valor, double perc_boost) {
        this.id = id;
        this.info = info;
        this.nome_boost = nome_boost;
        this.valor = valor;
        this.perc_boost = perc_boost;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getNomeBoost() {
        return nome_boost;
    }

    public void setNomeBoost(String nome_boost) {
        this.nome_boost = nome_boost;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getPercBoost() {
        return perc_boost;
    }

    public void setPercBoost(double perc_boost) {
        this.perc_boost = perc_boost;
    }
}
