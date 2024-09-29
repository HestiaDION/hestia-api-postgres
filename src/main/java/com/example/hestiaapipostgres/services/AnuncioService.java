package com.example.hestiaapipostgres.services;

import com.example.hestiaapipostgres.dto.RegisterUniversityDTO;
import com.example.hestiaapipostgres.models.Anuncio;
import com.example.hestiaapipostgres.models.Universitario;
import com.example.hestiaapipostgres.repository.AnuncioRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnuncioService {

    private final AnuncioRepository anuncioRepository;
    public AnuncioService(AnuncioRepository anuncioRepository){
        this.anuncioRepository = anuncioRepository;
    }

    // GET

    public List<Anuncio> listAllAds(){
        return anuncioRepository.findAll();
    }


    //  POST
    public Anuncio registerAd(Anuncio registerAd){

        if (!anuncioRepository.findAnuncioById(registerAd.getId()).isEmpty()){
            throw new EntityExistsException("Este registro já existe no banco!");
        }
        return anuncioRepository.save(registerAd);
    }
}
