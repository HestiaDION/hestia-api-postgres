package com.example.hestiaapipostgres.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "universitario_moradia")
public class UniversitarioMoradia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @JsonProperty("imovel_id")
    private UUID imovelId;

    @JsonProperty("universitario_id")
    private UUID universitarioId;

    public UniversitarioMoradia(UUID imovelId, UUID universitarioId) {
        this.imovelId = imovelId;
        this.universitarioId = universitarioId;
    }

    public UniversitarioMoradia(UUID id, UUID imovelId, UUID universitarioId) {
        this.id = id;
        this.imovelId = imovelId;
        this.universitarioId = universitarioId;
    }

    public UniversitarioMoradia() {

    }

    public UUID getImovelId() {
        return imovelId;
    }

    public void setImovelId(UUID imovelId) {
        this.imovelId = imovelId;
    }

    public UUID getUniversitarioId() {
        return universitarioId;
    }

    public void setUniversitarioId(UUID universitarioId) {
        this.universitarioId = universitarioId;
    }
}
