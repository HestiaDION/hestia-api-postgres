package com.example.hestiaapipostgres.repositories;


import com.example.hestiaapipostgres.models.Plano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlanoRepository extends JpaRepository<Plano, UUID> {

    Optional<Plano> findPlanoByTipo_plano(String tipoPlano);

    Optional<Plano> findPlanoById(UUID id);

    Optional<Plano> findPlanoByUserEmail(String email);

}
