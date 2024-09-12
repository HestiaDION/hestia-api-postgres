package com.example.hestiaapipostgres.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="universitario")
public class Universitario {

    @Id
    @Column(updatable = false, nullable = false)
    private UUID id;
    private Date dt_nascimento;

    private String nome;
    private String dne;
    private String bio;
    private UUID login_id;

}
