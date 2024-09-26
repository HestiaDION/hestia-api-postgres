package com.example.hestiaapipostgres.dto;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterAdDTO(

        @Size(min = 1, max = 255)
        String nome,
        @Size(min = 1, max = 255)
        String descricao,
        UUID imovel_id,
        LocalDate dt_inicio,
        LocalDate dt_termino,
        double aluguel

) {
}
