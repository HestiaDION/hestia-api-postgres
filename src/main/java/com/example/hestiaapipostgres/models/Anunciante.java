package com.example.hestiaapipostgres.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Anunciante {

    @Id
    @Column(updatable = false, nullable = false)
    private UUID id;

    private String nome;

    private String cpf;

    private String bio;


}
