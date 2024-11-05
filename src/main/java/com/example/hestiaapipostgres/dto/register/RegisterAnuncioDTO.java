package com.example.hestiaapipostgres.dto.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterAnuncioDTO(

        @NotNull(message = "O nome da anúncio não pode ser nulo")
        @NotBlank(message = "O nome do anúncio não deve estar em branco")
        String nome,
        @NotNull(message = "A descrição da anúncio não pode ser nula")
        @NotBlank(message = "A descrição do anúncio não deve estar em branco")
        String descricao,
        @NotNull(message = "A quantidade máxima de pessoas não deve estar nula")
        @JsonProperty("quantidade_maxima_pessoas")
        int quantidadeMaximaPessoas,
        @NotNull(message = "A quantidade de quartos não deve estar nula")
        @JsonProperty("quantidade_quartos")
        int quantidadeQuartos,
        @NotNull(message = "O universidade próxima não deve estar nula")
        @NotBlank(message = "A universidade próxima não deve estar branco")
        @JsonProperty("universidade_proxima")
        String universidadeProxima,
        @NotNull(message = "As regras da casa não devem estar nulas")
        @NotBlank(message = "As regras da casa não devem estar em branco")
        String regras,
        @NotNull(message = "A cidade não deve estar nula")
        @NotBlank(message = "A cidade não deve estar branco")
        String cidade,
        @NotNull(message = "O bairro não deve estar nulo")
        @NotBlank(message = "O bairro não deve estar em branco")
        String bairro,
        @NotNull(message = "A rua não deve estar nula")
        @NotBlank(message = "A rua não deve estar em branco")
        String rua,
        @NotNull(message = "O número da casa não deve ser nulo")
        String numero,
        @NotNull(message = "O CEP não deve estar nulo")
        @NotBlank(message = "O CEP não deve estar em branco")
        String cep,
        @NotNull(message = "O complemento não deve estar nulo")
        @NotBlank(message = "O complemento não deve estar branco")
        String complemento,
        @NotNull(message = "O valor de aluguel não deve estar nulo")
        Double aluguel,
        @NotNull(message = "O e-mail não pode ser nulo")
        @NotBlank(message = "O e-mail não deve estar em branco")
        @Email(message = "O e-mail fornecido não é válido")
        @JsonProperty("email_anunciante")
        String emailAnunciante

) {

}
