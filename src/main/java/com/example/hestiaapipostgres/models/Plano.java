package com.example.hestiaapipostgres.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Plano {
    @Id
    private UUID id;
    private String tipo_plano;
    private String info;
    private double valor;

    public Plano(){}
    public Plano(UUID id, String tipo_plano, String info, double valor){
        this.id = id;
        this.tipo_plano = tipo_plano;
        this.info = info;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTipoPlano() {
        return tipo_plano;
    }

    public void setTipoPlano(String tipo_plano) {
        this.tipo_plano = tipo_plano;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
