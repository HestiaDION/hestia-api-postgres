package com.example.hestiaapipostgres.repositories;


import com.example.hestiaapipostgres.models.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PlanoRepository extends JpaRepository<Plano, UUID> {

//    Optional<Plano> findPlanoByTipo_plano(String tipoPlano);

//    Optional<Plano> findPlanoById(UUID id);

    @Query(value = "SELECT * FROM get_planos_by_user_email(:email)", nativeQuery = true)
    Optional<Plano> findPlanoByUserEmail(@Param("email") String email);
}
