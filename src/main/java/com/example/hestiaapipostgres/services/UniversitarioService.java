package com.example.hestiaapipostgres.services;


import com.example.hestiaapipostgres.models.Universitario;
import com.example.hestiaapipostgres.repository.UniversitarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversitarioService {

    private final UniversitarioRepository universitarioRepository;

    public UniversitarioService(UniversitarioRepository universitarioRepository){
        this.universitarioRepository = universitarioRepository;
    }

    public List<Universitario> listAllUniversities(){
        return universitarioRepository.findAll();
    }
}
