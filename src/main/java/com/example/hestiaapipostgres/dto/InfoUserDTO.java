package com.example.hestiaapipostgres.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record InfoUserDTO(

        @NotNull(message = "O e-mail não pode estar vazio")
        @Email(message = "O e-mail fornecido não é válido")
        String email,
        String origem
) {
}
