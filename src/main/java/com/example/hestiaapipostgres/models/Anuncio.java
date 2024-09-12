package com.example.hestiaapipostgres.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Anuncio {

    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "UUID")


    private UUID id;

    @ManyToOne
    @JoinColumn(name = "anunciante_id", nullable = false)
    private Anunciante anunciante;

    private String descricao;

    private String nome;
    private UUID imovel_id;

}
