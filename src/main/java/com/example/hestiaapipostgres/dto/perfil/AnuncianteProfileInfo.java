package com.example.hestiaapipostgres.dto.perfil;

import java.time.LocalDate;

public record AnuncianteProfileInfo(
        String genero, String bio, LocalDate dt_nascimento

) {
}
