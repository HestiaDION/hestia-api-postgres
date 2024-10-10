package com.example.hestiaapipostgres.dto.register;

import com.example.hestiaapipostgres.models.Anunciante;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RegisterAdvertiserDTO(

        @NotNull(message = "O nome não pode ser nulo")
        @NotBlank(message = "O nome não deve estar em branco")
        @Size(message="O nome deve ter no mínimo 3 caracteres", min = 3)
        String nome,
        @NotNull(message = "O e-mail não pode ser nulo")
        @NotBlank(message = "O e-mail não deve estar em branco")
        @Email(message = "O e-mail fornecido não é válido")
        String email,
        @NotNull(message = "A cidade não pode ser nula")
        @NotBlank(message = "A cidade não deve estar em branco")
        String cidade,
        @NotNull(message = "O telefone não pode ser nulo")
        @NotBlank(message = "O telefone não deve estar em branco")
        @Size(message="O tamanho do telefone deve ser de 11 caracteres", min = 11, max = 11)
        String telefone,
        @NotNull(message = "O gênero não pode ser nulo")
        @NotBlank(message = "O gênero não deve estar em branco")
        @Size(message = "O gênero deve ter no máximo 10 caracteres")
        String genero,
        @JsonProperty("dt_nascimento")
        @NotNull(message = "A data de nascimento não deve ser nula")
        LocalDate dtNascimento

) {

    public Anunciante toAnunciante(){

       return new Anunciante(
               this.nome,
               this.cidade,
               this.telefone,
               this.dtNascimento,
               this.genero,
               this.email

       );

    }
}
