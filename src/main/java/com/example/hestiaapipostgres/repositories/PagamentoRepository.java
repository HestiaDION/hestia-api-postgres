package com.example.hestiaapipostgres.repositories;


import com.example.hestiaapipostgres.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {

    @Override
    Optional<Pagamento> findById(UUID uuid);

    Optional<Pagamento> findByEmailUsuarioAssinante(@Param("email") String email);

}
