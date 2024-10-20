package com.example.hestiaapipostgres.repositories;

import com.example.hestiaapipostgres.models.Anunciante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnuncianteRepository extends JpaRepository<Anunciante, UUID> {

    @Procedure(procedureName = "add_anunciante")
    void addAnunciante(
            @Param("email") String email,
            @Param("nome") String nome,
            @Param("dt_nascimento") LocalDate dtNascimento,
            @Param("bio") String bio,
            @Param("telefone") String telefone,
            @Param("genero") String genero,
            @Param("municipio") String universidade,
            @Param("prefixo") String prefixo

    );

    Optional<Anunciante> findAnuncianteById(UUID id);
    Optional<Anunciante> findAnuncianteByTelefone(String telefone);
    Optional<Anunciante> findAnuncianteByEmail(String email);
}
