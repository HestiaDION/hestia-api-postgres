package com.example.hestiaapipostgres.controllers;

import com.example.hestiaapipostgres.models.Anuncio;
import com.example.hestiaapipostgres.services.AnuncioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hestiaapi/postgres/ad")
public class AnuncioController {

    private final AnuncioService anuncioService;

    public AnuncioController(AnuncioService anuncioService){
        this.anuncioService = anuncioService;
    }

    //        =-=-=-=- GET -==--==-

    @GetMapping("/listAll")
    public List<Anuncio> findAllAds(){
        return anuncioService.listAllAds();
    }


    //       =--==-=-=- POST =--=--==

    @PostMapping("/register")
    public ResponseEntity<Anuncio> registerAd(@Valid @RequestBody Anuncio registerAd){

        Anuncio anuncio = anuncioService.registerAd(registerAd);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerAd);

    }

}
