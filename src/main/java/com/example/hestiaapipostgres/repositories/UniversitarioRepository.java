package com.example.hestiaapipostgres.repositories;

import com.example.hestiaapipostgres.models.Universitario;
import org.postgresql.util.PGobject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UniversitarioRepository extends JpaRepository<Universitario, UUID> {

    @Procedure(procedureName = "add_universitario")
    void addUniversitario(
            @Param("email") String email,
            @Param("nome") String nome,
            @Param("dt_nascimento") LocalDate dtNascimento,
            @Param("dne") String dne,
            @Param("municipio") String municipio,
            @Param("genero") String genero,
            @Param("prefixo") String prefixo,
            @Param("telefone") String telefone,
            @Param("universidade") String universidade,
            @Param("bio") String bio,
            @Param("tipo_conta") String tipoConta

    );

    Optional<Universitario> findUniversitarioById(UUID id);
    Optional<Universitario> findUniversitarioByDne(String dne);
    Optional<Universitario> findUniversitarioByEmail(String email);
    @Query(value = "SELECT get_user_uuid_by_email(:email)", nativeQuery = true)
    Object get_user_uuid_by_email(@Param("email") String email);
}