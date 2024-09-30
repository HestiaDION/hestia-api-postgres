package com.example.hestiaapipostgres.services;

import com.example.hestiaapipostgres.dto.register.RegisterUniversityDTO;
import com.example.hestiaapipostgres.dto.perfil.UniversitarioProfileInfo;
import com.example.hestiaapipostgres.dto.update.UpdateUniversityDTO;
import com.example.hestiaapipostgres.models.Universitario;
import com.example.hestiaapipostgres.repository.UniversitarioRepository;
import jakarta.persistence.EntityExistsException;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

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
                () -> new EntityNotFoundException("Univesitário não encontrado")

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
                () -> new EntityNotFoundException("Univesitário não encontrado")
        );
    }


    // POST
    public Universitario registerUniversity(RegisterUniversityDTO registerUniversityDTO){

        if (!universitarioRepository.findUniversitarioByDne(registerUniversityDTO.dne()).isEmpty()){
            throw new EntityExistsException("Este registro já existe no banco!");
        }
        return universitarioRepository.save(registerUniversityDTO.toUniversity());
    }


    // PATCH
    public Universitario updateUniversity(UUID id, UpdateUniversityDTO updateUniversityDTO){

        Optional<Universitario> universitarioExistente = universitarioRepository.findUniversitarioById(id);
        if(universitarioExistente.isEmpty()){
            throw new EntityNotFoundException("Este registro não existe no banco ou nessa tabela.");
        }

        Universitario universitario = universitarioExistente.get();

        // dar os sets para atualizar
        // verificando os campos, já que não há obrigatoriedade em atualizar todos eles juntos
        if(updateUniversityDTO.cidade() != null && !updateUniversityDTO.cidade().isEmpty()){
            universitario.setCidade(updateUniversityDTO.cidade());
        }
        if(updateUniversityDTO.telefone() != null && !updateUniversityDTO.telefone().isEmpty()){
            universitario.setTelefone(updateUniversityDTO.telefone());
        }
        if(updateUniversityDTO.bio() != null && !updateUniversityDTO.bio().isEmpty()){
            universitario.setBio(updateUniversityDTO.bio());
        }
        if(updateUniversityDTO.nome() != null && !updateUniversityDTO.nome().isEmpty()){
            universitario.setNome(updateUniversityDTO.nome());
        }

        return universitarioRepository.save(universitario);

    }


}
