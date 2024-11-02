package com.example.hestiaapipostgres.dto.get;

import com.nimbusds.jose.shaded.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.UUID;

public record ImovelAnuncioDTO(
        @SerializedName("imovel_id")
        UUID imovelId,
        String regras,
        String descricao,
        @SerializedName("quantidade_quartos")
        int quantidadeQuartos,
        @SerializedName("universidade_proxima")
        String universidadadeProxima,
        @SerializedName("quantidade_maxima_pessoas")
        int quantidadeMaximaPessoas,
        @SerializedName("nome")
        String nomeAnuncio,
        double aluguel,
        @SerializedName("dt_inicio")
        LocalDate dtInicio

) {
}
