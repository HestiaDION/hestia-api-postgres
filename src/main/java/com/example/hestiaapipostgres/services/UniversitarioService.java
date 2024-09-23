package com.example.hestiaapipostgres.services;


import com.example.hestiaapipostgres.dto.RegisterUniversityDTO;
import com.example.hestiaapipostgres.dto.UniversitarioProfileInfo;
import com.example.hestiaapipostgres.models.Universitario;
import com.example.hestiaapipostgres.repository.UniversitarioRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UniversitarioService {

    private final UniversitarioRepository universitarioRepository;

    public UniversitarioService(UniversitarioRepository universitarioRepository){
        this.universitarioRepository = universitarioRepository;
    }

    public List<Universitario> listAllUniversities(){
        return universitarioRepository.findAll();
    }

    public UniversitarioProfileInfo getInfoProfileByUniversity(UUID id){
        Universitario universitarioProfileInfo = universitarioRepository.findUniversitarioById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Univesitário não encontrado")

        );

        return new UniversitarioProfileInfo(
                universitarioProfileInfo.getGenero(),
                universitarioProfileInfo.getUniversidade(),
                universitarioProfileInfo.getBio(),
                universitarioProfileInfo.getDt_nascimento()
        );

    }

    public Universitario listUniversityById(UUID id){
        return universitarioRepository.findUniversitarioById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Univesitário não encontrado")
        );
    }

    // POST

    public Universitario registerUniversity(RegisterUniversityDTO registerUniversityDTO){

        if (!universitarioRepository.findUniversitarioByDne(registerUniversityDTO.dne()).isEmpty()){
            throw new EntityExistsException();
        }
        return universitarioRepository.save(registerUniversityDTO.toUniversity());
    }

}
