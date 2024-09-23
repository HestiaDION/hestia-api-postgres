package com.example.hestiaapipostgres.dto;

import com.example.hestiaapipostgres.models.Universitario;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


public record RegisterUniversityDTO(
        @NotNull(message = "O nome não pode ser nulo") @Size(message="O nome deve ter no mínimo 3 caracteres", min = 3) String nome,
        @NotNull(message = "A DNE não pode ser nula") String dne,
        @NotNull(message = "A bio não pode ser nulo") String bio,
        @NotNull(message = "A cidade não pode ser nulo") String cidade,
        @NotNull(message = "O telefone não pode ser nulo") @Size(message="O tamanho do telefone deve ser de 11 caracteres",
                min = 11, max = 11) String telefone,
        @NotNull(message = "A universidade não pode ser nulo") String universidade,
        @NotNull(message = "O gênero não pode ser nulo") String genero,

        @JsonProperty("dt_nascimento")
        @NotNull(message = "A data de nascimento não pode ser nulo") LocalDate dtNascimento

        ) {
        public Universitario toUniversity() {

                return new Universitario(
                       this.dtNascimento,
                        this.nome,
                        this.dne,
                        this.bio,
                        this.cidade,
                        this.telefone,
                        this.universidade,
                        this.genero

                );

        }
}
