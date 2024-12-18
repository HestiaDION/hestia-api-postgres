package com.example.hestiaapipostgres.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "pagamento_plano")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    @Schema(description = "Representa o UUID gerado do pagamento", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Schema(description = "Representa o nome do usuário assinante do plano", example = "Sophie Kumagai")
    @Column(name = "nome")
    @JsonProperty(value = "nome_usuario_assinante")
    private String nomeUsuarioAssinante;
    @Schema(description = "Representa o e-mail do usuário assinante do plano", example = "Ana Beatriz")
    @Column(name = "email")
    @JsonProperty(value = "email_usuario_assinante")
    private String emailUsuarioAssinante;
    @Schema(description = "Representa o ID do plano", example = "123e4567-e89b-12d3-a456-426614174000")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer"}) // isso aq ignora o proxy do Hibernate usado para Lazy Loading, evitando erro de serialização com Jackson
    @JoinColumn(name = "plano_id")
    private Plano plano;


    public Pagamento(){}

    public Pagamento(String nomeUsuarioAssinario, String emailUsuarioAssinante, Plano plano){
        this.nomeUsuarioAssinante = nomeUsuarioAssinario;
        this.emailUsuarioAssinante = emailUsuarioAssinante;
        this.plano = plano;
    }

    public Pagamento(UUID id, String nomeUsuarioAssinante, String emailUsuarioAssinante, Plano plano) {
        this.id = id;
        this.nomeUsuarioAssinante = nomeUsuarioAssinante;
        this.emailUsuarioAssinante = emailUsuarioAssinante;
        this.plano = plano;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeUsuarioAssinante() {
        return nomeUsuarioAssinante;
    }

    public void setNomeUsuarioAssinante(String nomeUsuarioAssinante) {
        this.nomeUsuarioAssinante = nomeUsuarioAssinante;
    }

    public String getEmailUsuarioAssinante() {
        return emailUsuarioAssinante;
    }

    public void setEmailUsuarioAssinante(String emailUsuarioAssinante) {
        this.emailUsuarioAssinante = emailUsuarioAssinante;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

}
