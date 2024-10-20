package com.example.hestiaapipostgres.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Universitario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    @Schema(description = "Representa o id do universitário", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Data de nascimento do universitário. Deve estar no formato YYYY-MM-DD", example = "2000-03-03")
    private LocalDate dt_nascimento;
    @Size(min = 3)
    @Schema(description = "Representa o nome do universitário", example = "Laura Farias")
    private String nome;
    @Schema(description = "Representa o Documento Nacional do Estudante (DNE) do universitário.", example = "USP")
    private String dne;
    @Schema(description = "Representa a biografia do universitário. É somente requisitada no UPDATE.", example = "Olá, sou o universitário Fulano!")
    private String bio;
    @Column(name="municipio")
    @Schema(description = "Representa a cidade do universitário.", example = "Guarulhos")
    private String municipio;
    @Size(min = 11, max = 11)
    @Schema(description = "Representa o telefone do universitário. Deve estar apenas em números.", example = "11958987896")
    private String telefone;
    @Schema(description = "Representa a universidade do universitário.", example = "USP")
    private String universidade;

    @Schema(description = "Representa o gênero do universitário", example = "Masculino")
    private String genero;

    // adicionando email: linkagem com o fireauth
    @Schema(description = "Representa o e-mail do universitário", example = "laura@gmail.com")
    private String email;

    @Size(min = 2, max = 2)
    private String prefixo;

    //TODO: implementar plano_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plano_id")
    private Plano plano;


    public Universitario(){}

    public Universitario(UUID id, LocalDate dt_nascimento, String nome, String dne, String municipio,
                         String telefone, String universidade, String genero, Plano plano) {
        this.id = id;
        this.dt_nascimento = dt_nascimento;
        this.nome = nome;
        this.dne = dne;
        this.municipio = municipio;
        this.telefone = telefone;
        this.universidade = universidade;
        this.genero = genero;
        this.plano = plano;

    }


    // construtor para registro
    public Universitario(LocalDate dtNascimento, String nome, String dne, String municipio, String telefone, String universidade, String genero, String email) {
        this.dt_nascimento = dtNascimento;
        this.nome = nome;
        this.dne = dne;
        this.municipio = municipio;
        this.telefone = telefone;
        this.universidade = universidade;
        this.genero = genero;
        this.email = email;
    }


    // construtor para comsulta de perfil
    public Universitario(String nome, String bio, String municipio, String telefone) {
        this.nome = nome;
        this.bio = bio;
        this.municipio = municipio;
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

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String cidade) {
        this.municipio = cidade;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public String getPrefixo() {
        return prefixo;
    }

    public void setPrefixo(String prefixo) {
        this.prefixo = prefixo;
    }
}
