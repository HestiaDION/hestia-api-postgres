package com.example.hestiaapipostgres.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Anuncio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;
    private String descricao;
    private String nome;
    private UUID imovel_id;
    private LocalDate dt_inicio;
    private LocalDate dt_termino;
    private double aluguel;
    @ManyToOne
    @JoinColumn(name = "anunciante_id", nullable = false)
    private Anunciante anunciante;
    // TODO: implementar anunciante_id
    // TODO: implementar imovel_id

    // novos campos adicionados --> confirmar com o pessoal de dados

//    private String regras;
//    private String restricoes;



    public Anuncio(UUID id, String descricao, String nome, UUID imovel_id, LocalDate dt_inicio, LocalDate dt_termino, double aluguel, Anunciante anunciante) {
        this.id = id;
        this.descricao = descricao;
        this.nome = nome;
        this.imovel_id = imovel_id;
        this.dt_inicio = dt_inicio;
        this.dt_termino = dt_termino;
        this.aluguel = aluguel;
        this.anunciante = anunciante;
    }

    public Anuncio() {}
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UUID getImovel_id() {
        return imovel_id;
    }

    public void setImovel_id(UUID imovel_id) {
        this.imovel_id = imovel_id;
    }

    public LocalDate getDt_inicio() {
        return dt_inicio;
    }

    public void setDt_inicio(LocalDate dt_inicio) {
        this.dt_inicio = dt_inicio;
    }

    public LocalDate getDt_termino() {
        return dt_termino;
    }

    public void setDt_termino(LocalDate dt_termino) {
        this.dt_termino = dt_termino;
    }

    public double getAluguel() {
        return aluguel;
    }

    public void setAluguel(double aluguel) {
        this.aluguel = aluguel;
    }

    public Anunciante getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(Anunciante anunciante) {
        this.anunciante = anunciante;
    }
}
