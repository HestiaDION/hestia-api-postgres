package com.example.hestiaapipostgres.controllers;

import com.example.hestiaapipostgres.dto.register.RegisterUniversityDTO;

import com.example.hestiaapipostgres.dto.perfil.UniversitarioProfileInfo;
import com.example.hestiaapipostgres.dto.update.UpdateUniversityDTO;
import com.example.hestiaapipostgres.models.Universitario;
import com.example.hestiaapipostgres.services.UniversitarioService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/university")
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

    @GetMapping("/profile/{email}")
    public ResponseEntity<UniversitarioProfileInfo> getProfileByUniversity(@PathVariable String email){
        UniversitarioProfileInfo universitarioProfileInfo = universitarioService.getInfoProfileByUniversity(email);
        return ResponseEntity.ok().body(universitarioProfileInfo);
    }

    //                        =-=-=-= POSTS =-=-=-=

    @PostMapping("/register")
    public ResponseEntity<Universitario> registerUniversity(@Valid @RequestBody RegisterUniversityDTO registerUniversityDTO){

        Universitario universitario = universitarioService.registerUniversity(registerUniversityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(universitario);

    }


    //                   =--==--===- PATCH -==-=-=-=-=-

    @PatchMapping("/updateProfile/{email}")
    public ResponseEntity<Universitario> updateUniversity(@PathVariable String email, @Valid @RequestBody UpdateUniversityDTO updateUniversityDTO){

        Universitario universitario = universitarioService.updateUniversity(email, updateUniversityDTO);

        return ResponseEntity.status(HttpStatus.OK).body(universitario);
    }

}
