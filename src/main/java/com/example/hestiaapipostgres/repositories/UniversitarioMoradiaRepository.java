package com.example.hestiaapipostgres.repositories;

import com.example.hestiaapipostgres.models.Universitario;
import com.example.hestiaapipostgres.models.UniversitarioMoradia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UniversitarioMoradiaRepository extends JpaRepository<UniversitarioMoradia, UUID> {

    Optional<UniversitarioMoradia> findUniversitarioMoradiaByImovelId(UUID imovelId);

    @Query("SELECT u FROM Universitario u JOIN UniversitarioMoradia um ON u.id = um.universitarioId " +
            "JOIN Anuncio a ON a.imovel.id = um.imovelId " +
            "WHERE a.imovel.id = :imovelId")
    List<Universitario> findUniversitariosByImovelId(@Param("imovelId") UUID imovelId);

}
