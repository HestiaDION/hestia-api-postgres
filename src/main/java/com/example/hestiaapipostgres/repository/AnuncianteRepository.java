package com.example.hestiaapipostgres.repository;

import com.example.hestiaapipostgres.models.Anunciante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnuncianteRepository extends JpaRepository<Anunciante, UUID> {

    Optional<Anunciante> findAnuncianteById(UUID id);
}
