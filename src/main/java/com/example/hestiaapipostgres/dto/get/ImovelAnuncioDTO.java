package com.example.hestiaapipostgres.dto.get;

import java.time.LocalDate;
import java.util.UUID;

public record ImovelAnuncioDTO(
        UUID imovelId,
        String regras,
        String descricao,
        int quantidadeQuartos,
        String universidadadeProxima,
        int quantidadeMaximaPessoas,
        String nomeAnuncio,
        double aluguel,
        LocalDate dtInicio

) {
}
