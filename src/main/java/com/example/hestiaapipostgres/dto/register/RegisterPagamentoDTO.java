package com.example.hestiaapipostgres.dto.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RegisterPagamentoDTO(

        @NotNull(message = "O nome do usuário assinante não pode ser nulo")
        @NotBlank(message = "O nome do usuário assinante não pode estar em branco")
        @JsonProperty("nome")
        String nomeUsuarioAssinante,
        @NotNull(message = "O e-mail do usuário assinante não pode ser nulo")
        @NotBlank(message = "O e-mail do usuário assinante não deve estar em branco")
        @JsonProperty("email")
        String emailUsuarioAssinante

) {


}
