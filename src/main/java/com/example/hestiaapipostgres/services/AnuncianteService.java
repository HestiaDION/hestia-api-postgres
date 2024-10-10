package com.example.hestiaapipostgres.services;


import com.example.hestiaapipostgres.dto.register.RegisterAdvertiserDTO;
import com.example.hestiaapipostgres.dto.update.UpdateAdvertiserDTO;
import com.example.hestiaapipostgres.dto.perfil.AnuncianteProfileInfo;
import com.example.hestiaapipostgres.models.Anunciante;
import com.example.hestiaapipostgres.repository.AnuncianteRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnuncianteService {

    private final AnuncianteRepository anuncianteRepository;

    public AnuncianteService(AnuncianteRepository anuncianteRepository) {
        this.anuncianteRepository = anuncianteRepository;
    }
    public Anunciante listAdvertiserById(UUID id){
        return anuncianteRepository.findAnuncianteById(id).orElseThrow(
                () -> new EntityNotFoundException("Anunciante não encontrado")
        );
    }

    // GETS
    public List<Anunciante> listAllAdvertisers(){
        return anuncianteRepository.findAll();
    }

    public AnuncianteProfileInfo getInfoProfileByAdvertiser(String email){
        Anunciante anuncianteProfileInfo = anuncianteRepository.findAnuncianteByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("Anunciante não encontrado")

        );

        return new AnuncianteProfileInfo(
                anuncianteProfileInfo.getGenero(),
                anuncianteProfileInfo.getBio(),
                anuncianteProfileInfo.getDtNascimento()
        );

    }


    // POST
    public Anunciante registerAdvertiser(RegisterAdvertiserDTO registerAdvertiserDTO){

        if (!anuncianteRepository.findAnuncianteByTelefone(registerAdvertiserDTO.telefone()).isEmpty()){
            throw new EntityExistsException("Este registro já existe no banco!");
        }

        if(!anuncianteRepository.findAnuncianteByEmail(registerAdvertiserDTO.email()).isEmpty()){
            throw new EntityExistsException("Este registro já existe no banco!");
        }

        return anuncianteRepository.save(registerAdvertiserDTO.toAnunciante());
    }

    // PATCH
    public Anunciante updateAdvertiser(String email, UpdateAdvertiserDTO updateAdvertiserDTO){

        Optional<Anunciante> anuncianteExistente = anuncianteRepository.findAnuncianteByEmail(email);
        if(anuncianteExistente.isEmpty()){
            throw new EntityNotFoundException("Este registro não existe no banco ou nessa tabela.");
        }

        Anunciante anunciante = anuncianteExistente.get();

        // dar os sets para atualizar
        // verificando os campos, já que não há obrigatoriedade em atualizar todos eles juntos
        if(updateAdvertiserDTO.cidade() != null && !updateAdvertiserDTO.cidade().isEmpty()){
            anunciante.setCidade(updateAdvertiserDTO.cidade());
        }
        if((updateAdvertiserDTO.bio() != null && !(updateAdvertiserDTO.bio().isEmpty()))){
            anunciante.setBio((updateAdvertiserDTO.bio()));
        }
        if((updateAdvertiserDTO.nome() != null && !(updateAdvertiserDTO.nome().isEmpty()))){
            anunciante.setNome((updateAdvertiserDTO.nome()));
        }

        return anuncianteRepository.save(anunciante);

    }

}
