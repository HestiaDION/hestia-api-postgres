package com.example.hestiaapipostgres.repository;

import com.example.hestiaapipostgres.models.Universitario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository

public interface UniversitarioRepository extends JpaRepository<Universitario, UUID> {
    Optional<Universitario> findUniversitarioById(UUID id);
    Optional<Universitario> findUniversitarioByDne(String dne);
    Optional<Universitario> findUniversitarioByEmail(String email);



}
