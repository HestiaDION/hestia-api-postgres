package com.example.hestiaapipostgres.services;


import com.example.hestiaapipostgres.models.Anunciante;
import com.example.hestiaapipostgres.repository.AnuncianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnuncianteService {

    private final AnuncianteRepository anuncianteRepository;

    public AnuncianteService(AnuncianteRepository anuncianteRepository) {
        this.anuncianteRepository = anuncianteRepository;
    }

    public List<Anunciante> listAllAdvertisers(){
        return anuncianteRepository.findAll();
    }



}
