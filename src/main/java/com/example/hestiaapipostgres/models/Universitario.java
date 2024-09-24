package com.example.hestiaapipostgres.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.boot.autoconfigure.web.WebProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
public class Universitario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;
    private LocalDate dt_nascimento;
    @Size(min = 3)
    private String nome;
    private String dne;
    private String bio;
    private String cidade;
    @Size(min = 11, max = 11)
    private String telefone;
    private String universidade;
    private String genero;
    //TODO: implementar plano_id


    public Universitario(){}

    public Universitario(UUID id, LocalDate dt_nascimento, String nome, String dne, String cidade,
                         String telefone, String universidade, String genero) {
        this.id = id;
        this.dt_nascimento = dt_nascimento;
        this.nome = nome;
        this.dne = dne;
        this.cidade = cidade;
        this.telefone = telefone;
        this.universidade = universidade;
        this.genero = genero;

    }


    // construtor para registro
    public Universitario(LocalDate dtNascimento, String nome, String dne, String cidade, String telefone, String universidade, String genero) {
        this.dt_nascimento = dtNascimento;
        this.nome = nome;
        this.dne = dne;
        this.cidade = cidade;
        this.telefone = telefone;
        this.universidade = universidade;
        this.genero = genero;
    }

    public Universitario(String nome, String bio, String cidade, String telefone) {
        this.nome = nome;
        this.bio = bio;
        this.cidade = cidade;
        this.telefone = telefone;
    }




    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(LocalDate dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDne() {
        return dne;
    }

    public void setDne(String dne) {
        this.dne = dne;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }
}
