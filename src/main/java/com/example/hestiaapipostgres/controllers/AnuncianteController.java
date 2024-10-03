package com.example.hestiaapipostgres.controllers;


import com.example.hestiaapipostgres.dto.perfil.AnuncianteProfileInfo;
import com.example.hestiaapipostgres.dto.register.RegisterAdvertiserDTO;
import com.example.hestiaapipostgres.dto.update.UpdateAdvertiserDTO;
import com.example.hestiaapipostgres.models.Anunciante;

import com.example.hestiaapipostgres.services.AnuncianteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/advertiser")

public class AnuncianteController {
    private final AnuncianteService anuncianteService;

    public AnuncianteController(AnuncianteService anuncianteService){
        this.anuncianteService = anuncianteService;
    }


    //          =-=-=- GETS =-=-=--=

    @GetMapping("/find/{id}")
    public Anunciante FindAdvertiserById(@PathVariable UUID id){
        return anuncianteService.listAdvertiserById(id);
    }

    @GetMapping("findAll")
    public List<Anunciante> findAllAdvertisers(){
        return anuncianteService.listAllAdvertisers();
    }

    @GetMapping("/profile/{email}")
    public ResponseEntity<AnuncianteProfileInfo> getProfileByUniversity(@PathVariable String email){

        AnuncianteProfileInfo anuncianteProfileInfo = anuncianteService.getInfoProfileByAdvertiser(email);
        return ResponseEntity.ok().body(anuncianteProfileInfo);
    }
    //            =--=-= POSTS -=--=-=-

    @PostMapping("/register")
    public ResponseEntity<Anunciante> registerAdvertiser(@Valid @RequestBody RegisterAdvertiserDTO registerAdvertiserDTO){

        Anunciante anunciante = anuncianteService.registerAdvertiser(registerAdvertiserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(anunciante);

    }


    //         =-=-=-= PATCH =-=-==-
    @PatchMapping("/updateProfile/{email}")
    public ResponseEntity<Anunciante> updateAdvertiser(@PathVariable String email, @Valid @RequestBody UpdateAdvertiserDTO updateAdvertiserDTO){

        Anunciante anunciante = anuncianteService.updateAdvertiser(email, updateAdvertiserDTO);
        return ResponseEntity.status(HttpStatus.OK).body(anunciante);
    }

}
