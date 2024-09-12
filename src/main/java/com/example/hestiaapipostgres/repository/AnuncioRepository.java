package com.example.hestiaapipostgres.repository;

import com.example.hestiaapipostgres.models.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface AnuncioRepository extends JpaRepository<Anuncio, UUID> {
}