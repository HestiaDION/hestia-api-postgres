package com.example.hestiaapipostgres.dto;

import java.time.LocalDate;

public record UniversitarioProfileInfo(
        String genero, String universidade, String bio, LocalDate dt_nascimento
) {


}
