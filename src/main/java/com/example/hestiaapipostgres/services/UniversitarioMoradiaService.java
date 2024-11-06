package com.example.hestiaapipostgres.services;


import com.example.hestiaapipostgres.dto.register.RegisterUniversityPropertyDTO;
import com.example.hestiaapipostgres.models.Universitario;
import com.example.hestiaapipostgres.models.UniversitarioMoradia;
import com.example.hestiaapipostgres.repositories.AnuncioRepository;
import com.example.hestiaapipostgres.repositories.UniversitarioMoradiaRepository;
import com.example.hestiaapipostgres.repositories.UniversitarioRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UniversitarioMoradiaService {

    private final UniversitarioMoradiaRepository universitarioMoradiaRepository;
    private final UniversitarioRepository universitarioRepository;
    private final AnuncioRepository anuncioRepository;

    public UniversitarioMoradiaService(UniversitarioMoradiaRepository universitarioMoradiaRepository, UniversitarioRepository universitarioRepository, AnuncioRepository anuncioRepository){
        this.universitarioMoradiaRepository = universitarioMoradiaRepository;
        this.universitarioRepository = universitarioRepository;
        this.anuncioRepository = anuncioRepository;
    }

    // GET
    public List<Universitario> listUniversitariosByImovelId(UUID imovelId) {
        return universitarioMoradiaRepository.findUniversitariosByImovelId(imovelId);
    }


    // POST

    public UniversitarioMoradia entrarEmUmaMoradia(RegisterUniversityPropertyDTO universitarioMoradia){

        if(universitarioRepository.findUniversitarioById(universitarioMoradia.universitarioId()).isEmpty()){
            throw new EntityNotFoundException("Este universitário não existe no banco.");
        }

        if(anuncioRepository.findAnuncioByImovelId(universitarioMoradia.imovelId()).isEmpty()){
            throw new EntityNotFoundException("Este imóvel não existe no banco.");
        }

        UniversitarioMoradia universitarioMoradiaInserido = new UniversitarioMoradia(universitarioMoradia.imovelId(), universitarioMoradia.universitarioId());
        universitarioMoradiaRepository.save(universitarioMoradiaInserido);

        return universitarioMoradiaRepository.findUniversitarioMoradiaByImovelId(universitarioMoradia.imovelId())
                .orElseThrow(() -> new EntityExistsException("Erro ao tentar entrar na moradia para o universitário: " + universitarioMoradia.universitarioId()));
    }

}
