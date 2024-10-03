package com.example.hestiaapipostgres.dto.update;


import jakarta.validation.constraints.Size;

public record UpdateAdvertiserDTO(

        @Size(message="O nome deve ter no mínimo 3 caracteres", min = 3)
        String nome,
        String bio,
        String cidade,
        @Size(message="O tamanho do telefone deve ser de 11 caracteres", min = 11, max = 11)
        String telefone
) {
}
