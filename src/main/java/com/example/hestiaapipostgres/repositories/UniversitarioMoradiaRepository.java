package com.example.hestiaapipostgres.repositories;

import com.example.hestiaapipostgres.models.UniversitarioMoradia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UniversitarioMoradiaRepository extends JpaRepository<UniversitarioMoradia, UUID> {

    Optional<UniversitarioMoradia> findUniversitarioMoradiaByImovelId(UUID imovelId);

}
