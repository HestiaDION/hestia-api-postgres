package com.example.hestiaapipostgres.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Anunciante {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    @Schema(description = "Representa o id do anunciante", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Schema(description = "Representa o nome do anunciante", example = "Sophie Kumagai")
    private String nome;
    @Schema(description = "Representa o e-mail do anunciante", example = "sophie@hotmail.com")
    private String email;
    @Schema(description = "Representa a biografia do anunciante. É somente requisitada no UPDATE.", example = "Olá, sou o anunciante Fulano!")
    private String bio;
    @Schema(description = "Data de nascimento do anunciante. Deve estar no formato YYYY-MM-DD", example = "2000-03-03")
    private LocalDate dt_nascimento;

    @Size(min = 11, max = 11)
    @Schema(description = "Representa o telefone do anunciante. Deve estar apenas em números.", example = "11958987896")
    private String telefone;

    @Schema(description = "Representa a cidade do anunciante.", example = "Guarulhos")
    @Column(name="municipio")
    private String municipio;
    @Schema(description = "Representa o gênero do anunciante", example = "Feminino")
    private String genero;

    @Size(min = 2, max = 2)
    private String prefixo;

    //TODO: implementar plano_id
    public Anunciante(){}
    public Anunciante(UUID id, String nome, String bio, LocalDate dt_nascimento, String telefone, String cidade, String genero, String email) {
        this.id = id;
        this.nome = nome;
        this.bio = bio;
        this.dt_nascimento = dt_nascimento;
        this.telefone = telefone;
        this.municipio = cidade;
        this.genero = genero;
        this.email = email;

    }

    public Anunciante(String nome, String cidade, String telefone, LocalDate dtNascimento, String genero, String email) {
        this.nome = nome;
        this.municipio = cidade;
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

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String cidade) {
        this.municipio = cidade;
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

    public String getPrefixo() {
        return prefixo;
    }

    public void setPrefixo(String prefixo) {
        this.prefixo = prefixo;
    }
}
