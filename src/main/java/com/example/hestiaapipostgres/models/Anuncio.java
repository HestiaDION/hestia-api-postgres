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

    private String nome;

    @OneToOne
    @JoinColumn(name = "imovel_id", referencedColumnName = "id", unique = true)
    private Imovel imovel;

    private LocalDate dt_inicio;
    private LocalDate dt_termino;
    private double aluguel;

    @ManyToOne
    @JoinColumn(name = "anunciante_id", nullable = false)
    private Anunciante anunciante;


    public Anuncio(UUID id, String nome, LocalDate dt_inicio, LocalDate dt_termino, double aluguel, Anunciante anunciante, Imovel imovel) {
        this.id = id;
        this.nome = nome;
        this.dt_inicio = dt_inicio;
        this.dt_termino = dt_termino;
        this.aluguel = aluguel;
        this.anunciante = anunciante;
        this.imovel = imovel;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Anuncio() {}
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
