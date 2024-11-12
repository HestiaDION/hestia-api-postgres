package com.example.hestiaapipostgres.dto.get;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nimbusds.jose.shaded.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public record ImovelAnuncioDTO (
        @JsonProperty("id")
        UUID anuncioId,
        String regras,
        String descricao,
        @JsonProperty("quantidade_quartos")
        int quantidadeQuartos,
        @JsonProperty("universidade_proxima")
        String universidadadeProxima,
        @JsonProperty("quantidade_maxima_pessoas")
        int quantidadeMaximaPessoas,
        @JsonProperty("nome")
        String nomeAnuncio,
        double aluguel,
        @JsonProperty("dt_inicio")
        LocalDate dtInicio,
        @JsonProperty("email_anunciante")
        String emailAnunciante,
        @JsonProperty("anunciante_id")
        UUID anuncianteId

) implements Serializable {
        private static final long serialVersionUID = 1L;
}
