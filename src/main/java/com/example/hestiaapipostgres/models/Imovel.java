package com.example.hestiaapipostgres.models;

import jakarta.persistence.*;

import java.util.UUID;


@Entity
public class Imovel {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String regras;
    private String descricao;

    @Column(name = "quantidade_quartos")
    private int quantidadeQuartos;

    @Column(name = "universidade_proxima")
    private String universidadeProxima;

    @Column(name = "quantidade_maxima_pessoas")
    private int quantidadeMaximaPessoas;

    @Column(name = "endereco_id")
    private UUID enderecoId;

    @OneToOne(mappedBy = "imovel")
    private Anuncio anuncio;



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRegras() {
        return regras;
    }

    public void setRegras(String regras) {
        this.regras = regras;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidadeQuartos() {
        return quantidadeQuartos;
    }

    public void setQuantidadeQuartos(int quantidadeQuartos) {
        this.quantidadeQuartos = quantidadeQuartos;
    }

    public String getUniversidadeProxima() {
        return universidadeProxima;
    }

    public void setUniversidadeProxima(String universidadeProxima) {
        this.universidadeProxima = universidadeProxima;
    }

    public int getQuantidadeMaximaPessoas() {
        return quantidadeMaximaPessoas;
    }

    public void setQuantidadeMaximaPessoas(int quantidadeMaximaPessoas) {
        this.quantidadeMaximaPessoas = quantidadeMaximaPessoas;
    }

    public UUID getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(UUID enderecoId) {
        this.enderecoId = enderecoId;
    }


    @Override
    public String toString() {
        return "Imovel{" +
                "id=" + id +
                ", regras='" + regras + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidadeQuartos=" + quantidadeQuartos +
                ", universidadadeProxima='" + universidadeProxima + '\'' +
                ", quantidadeMaximaPessoas=" + quantidadeMaximaPessoas +
                ", enderecoId=" + enderecoId +
                '}';
    }
}
