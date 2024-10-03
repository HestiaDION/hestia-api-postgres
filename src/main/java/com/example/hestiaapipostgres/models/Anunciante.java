package com.example.hestiaapipostgres.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Anunciante {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;
    private String nome;
    private String email;
    private String bio;
    private LocalDate dt_nascimento;
    private String telefone;
    private String cidade;
    private String genero;
    //TODO: implementar plano_id
    public Anunciante(){}
    public Anunciante(UUID id, String nome, String bio, LocalDate dt_nascimento, String telefone, String cidade, String genero, String email) {
        this.id = id;
        this.nome = nome;
        this.bio = bio;
        this.dt_nascimento = dt_nascimento;
        this.telefone = telefone;
        this.cidade = cidade;
        this.genero = genero;
        this.email = email;

    }

    public Anunciante(String nome, String cidade, String telefone, LocalDate dtNascimento, String genero, String email) {
        this.nome = nome;
        this.cidade = cidade;
        this.telefone = telefone;
        this.genero = genero;
        this.dt_nascimento = dtNascimento;
        this.email = email;
    }

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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDate getDtNascimento() {
        return dt_nascimento;
    }

    public void setDtNascimento(LocalDate dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(LocalDate dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }
}
