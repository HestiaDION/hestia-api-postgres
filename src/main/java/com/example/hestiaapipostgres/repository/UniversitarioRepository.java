package com.example.hestiaapipostgres.repository;

import com.example.hestiaapipostgres.models.Anunciante;
import com.example.hestiaapipostgres.models.Universitario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface UniversitarioRepository extends JpaRepository<Universitario, UUID> {
    Universitario findUniversitarioById(UUID id);

}
