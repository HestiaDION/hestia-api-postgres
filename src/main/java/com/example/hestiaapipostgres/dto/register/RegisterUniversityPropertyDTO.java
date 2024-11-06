package com.example.hestiaapipostgres.dto.register;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record RegisterUniversityPropertyDTO(

        @JsonProperty("universitario_id")
        UUID universitarioId,
        @JsonProperty("imovel_id")
        UUID imovelId
) {
}
