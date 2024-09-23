package com.example.hestiaapipostgres.controllers;


import com.example.hestiaapipostgres.models.Anunciante;

import com.example.hestiaapipostgres.services.AnuncianteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hestiaapi/postgres/advertiser")

public class AnuncianteController {
    private final AnuncianteService anuncianteService;

    public AnuncianteController(AnuncianteService anuncianteService){
        this.anuncianteService = anuncianteService;
    }

    @GetMapping("/findAdvertiverById")
    public Anunciante FindAdvertiserById(UUID id){
        return anuncianteService.listAdvertiserById(id);
    }

    @GetMapping("findAll")
    public List<Anunciante> findAllAdvertisers(){
        return anuncianteService.listAllAdvertisers();
    }


}
