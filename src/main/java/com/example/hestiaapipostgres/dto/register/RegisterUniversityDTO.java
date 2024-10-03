package com.example.hestiaapipostgres.dto.register;

import com.example.hestiaapipostgres.models.Universitario;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


public record RegisterUniversityDTO(
        @NotNull(message = "O nome não pode ser nulo")
        @NotBlank(message = "O nome não deve estar em branco")
        @Size(message="O nome deve ter no mínimo 3 caracteres", min = 3)
        String nome,

        @NotNull(message = "o e-mail não pode ser nulo")
        @NotBlank(message = "O e-mail não deve estar em branco")
        @Email(message = "O e-mail fornecido não á válido")
        String email,
        @NotNull(message = "A DNE não pode ser nula")
        @NotBlank(message = "O nome não deve estar em branco")
        String dne,
        @NotNull(message = "A cidade não pode ser nulo")
        @NotBlank(message = "O nome não deve estar em branco")
        String cidade,
        @NotNull(message = "O telefone não pode ser nulo")
        @NotBlank(message = "O telefone não deve estar em branco")
        @Size(message="O tamanho do telefone deve ser de 11 caracteres", min = 11, max = 11)
        String telefone,
        @NotNull(message = "A universidade não pode ser nula")
        @NotBlank(message = "O universidade não deve estar em branco")
        String universidade,
        @NotNull(message = "O gênero não pode ser nulo")
        @NotBlank(message = "O gênero não deve estar em branco")
        @Size(message = "O gênero deve ter no máximo 10 caracteres")
        String genero,
        @JsonProperty("dt_nascimento")
        @NotNull(message = "A data de nascimento não pode ser nula")
        LocalDate dtNascimento

        ) {
        public Universitario toUniversity() {

                return new Universitario(
                       this.dtNascimento,
                        this.nome,
                        this.dne,
                        this.cidade,
                        this.telefone,
                        this.universidade,
                        this.genero,
                        this.email

                );

        }
}
