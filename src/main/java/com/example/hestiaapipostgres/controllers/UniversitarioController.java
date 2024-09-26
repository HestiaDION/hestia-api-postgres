package com.example.hestiaapipostgres.controllers;

import com.example.hestiaapipostgres.dto.RegisterUniversityDTO;

import com.example.hestiaapipostgres.dto.UniversitarioProfileInfo;
import com.example.hestiaapipostgres.dto.UpdateUniversityDTO;
import com.example.hestiaapipostgres.models.Universitario;
import com.example.hestiaapipostgres.services.UniversitarioService;
import jakarta.persistence.EntityNotFoundException;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hestiaapi/postgres/university")
public class UniversitarioController {

    private final UniversitarioService universitarioService;

    public UniversitarioController(UniversitarioService universitarioService){
        this.universitarioService = universitarioService;
    }

    //                        ==--=--= GETS -==-=-=-=-

    @GetMapping("/listAll")
    public List<Universitario> findAllUniversities(){
        return universitarioService.listAllUniversities();
    }

    @GetMapping("/find/{id}")
    public Universitario findUniversityById(@PathVariable UUID id){
       return universitarioService.listUniversityById(id);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UniversitarioProfileInfo> getProfileByUniversity(@PathVariable UUID id){
        UniversitarioProfileInfo universitarioProfileInfo = universitarioService.getInfoProfileByUniversity(id);
        return ResponseEntity.ok().body(universitarioProfileInfo);
    }

    //                        =-=-=-= POSTS =-=-=-=

    @PostMapping("/register")
    public ResponseEntity<Universitario> registerUniversity(@Valid @RequestBody RegisterUniversityDTO registerUniversityDTO){

        Universitario universitario = universitarioService.registerUniversity(registerUniversityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(universitario);

    }


    //                   =--==--===- PATCH -==-=-=-=-=-

    @PatchMapping("/updateProfile/{id}")
    public ResponseEntity<Universitario> updateUniversity(@PathVariable UUID id, @Valid @RequestBody UpdateUniversityDTO updateUniversityDTO){

        Universitario universitario = universitarioService.updateUniversity(id, updateUniversityDTO);

        return ResponseEntity.status(HttpStatus.OK).body(universitario);
    }

}
