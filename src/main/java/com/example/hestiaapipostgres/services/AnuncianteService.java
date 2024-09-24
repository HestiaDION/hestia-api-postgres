package com.example.hestiaapipostgres.services;


import com.example.hestiaapipostgres.models.Anunciante;
import com.example.hestiaapipostgres.repository.AnuncianteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AnuncianteService {

    private final AnuncianteRepository anuncianteRepository;

    public AnuncianteService(AnuncianteRepository anuncianteRepository) {
        this.anuncianteRepository = anuncianteRepository;
    }
    public Anunciante listAdvertiserById(UUID id){
        return anuncianteRepository.findAnuncianteById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anunciante não encontrado")
        );
    }

    public List<Anunciante> listAllAdvertisers(){
        return anuncianteRepository.findAll();
    }



}
