package com.example.hestiaapipostgres.dto.perfil;

import java.io.Serializable;
import java.time.LocalDate;

public record UniversitarioProfileInfo(
        String genero, String universidade, String bio, LocalDate dt_nascimento
) implements Serializable {

    private static final long serialVersionUID = 1L;
}
