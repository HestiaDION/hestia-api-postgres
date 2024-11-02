package com.example.hestiaapipostgres.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

@Entity
public class Plano {
    @Id
    private UUID id;
    private String info;
    private double valor;
    @Column(name = "tipo_conta")
    private String tipoConta;
    private String descricao;

    // relacionamentos inversos
    @OneToMany(mappedBy = "plano") // Relacionamento inverso
    private List<Universitario> universitarios;
    @OneToMany(mappedBy = "plano")
    private List<Anunciante> anunciantes;
    @OneToMany(mappedBy = "plano")
    private List<Pagamento> pagamentos;

    public Plano(){}
    public Plano(UUID id, String info, double valor){
        this.id = id;
        this.info = info;
    }

    public Plano(UUID id, String info, double valor, String tipoConta,
                 String descricao, List<Universitario> universitarios, List<Anunciante> anunciantes, List<Pagamento> pagamentos) {
        this.id = id;
        this.info = info;
        this.valor = valor;
        this.tipoConta = tipoConta;
        this.descricao = descricao;
        this.universitarios = universitarios;
        this.anunciantes = anunciantes;
        this.pagamentos = pagamentos;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
