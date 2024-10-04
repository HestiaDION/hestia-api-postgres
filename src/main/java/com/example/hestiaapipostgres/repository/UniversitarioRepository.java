package com.example.hestiaapipostgres.repository;

import com.example.hestiaapipostgres.models.Universitario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UniversitarioRepository extends JpaRepository<Universitario, UUID> {

    @Procedure(name = "add_universitario", procedureName = "add_universitario")
    void addUniversitario(
            @Param("p_email") String email,
            @Param("p_nome") String nome,
            @Param("p_dt_nascimento") LocalDate dtNascimento,
            @Param("p_dne") String dne,
            @Param("p_municipio") String municipio,
            @Param("p_genero") String genero,
            @Param("p_telefone") String telefone,
            @Param("p_universidade") String universidade,
            @Param("p_bio") String bio
    );

    Optional<Universitario> findUniversitarioById(UUID id);
    Optional<Universitario> findUniversitarioByDne(String dne);
    Optional<Universitario> findUniversitarioByEmail(String email);
}