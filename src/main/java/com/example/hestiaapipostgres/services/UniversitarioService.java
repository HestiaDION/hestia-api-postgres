package com.example.hestiaapipostgres.services;

import com.example.hestiaapipostgres.dto.register.RegisterUniversityDTO;
import com.example.hestiaapipostgres.dto.perfil.UniversitarioProfileInfo;
import com.example.hestiaapipostgres.dto.update.UpdateUniversityDTO;
import com.example.hestiaapipostgres.models.Universitario;
import com.example.hestiaapipostgres.repositories.UniversitarioRepository;
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

    public UniversitarioProfileInfo getInfoProfileByUniversity(String email){
        Universitario universitarioProfileInfo = universitarioRepository.findUniversitarioByEmail(email).orElseThrow(
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
    public Universitario registerUniversity(RegisterUniversityDTO registerUniversityDTO) {


        if (universitarioRepository.findUniversitarioByDne(registerUniversityDTO.dne()).isPresent()) {
            throw new EntityExistsException("Este registro já existe no banco!");
        }

        if (universitarioRepository.findUniversitarioByEmail(registerUniversityDTO.email()).isPresent()) {
            throw new EntityExistsException("Este registro já existe no banco!");
        }

        universitarioRepository.addUniversitario(
                registerUniversityDTO.email(),
                registerUniversityDTO.nome(),
                registerUniversityDTO.dtNascimento(),
                registerUniversityDTO.dne(),
                registerUniversityDTO.municipio(),
                registerUniversityDTO.genero(),
                registerUniversityDTO.telefone(),
                registerUniversityDTO.universidade(),
                ""
        );

        // Retorna o universitário registrado
        return universitarioRepository.findUniversitarioByEmail(registerUniversityDTO.email())
                .orElseThrow(() -> new EntityExistsException("Erro ao inserir o universitário"));
    }


    // PATCH
    public Universitario updateUniversity(String email, UpdateUniversityDTO updateUniversityDTO){

        Optional<Universitario> universitarioExistente = universitarioRepository.findUniversitarioByEmail(email);
        if(universitarioExistente.isEmpty()){
            throw new EntityNotFoundException("Este registro não existe no banco ou nessa tabela: "+ email);
        }

        Universitario universitario = universitarioExistente.get();

        // dar os sets para atualizar
        // verificando os campos, já que não há obrigatoriedade em atualizar todos eles juntos
        if(updateUniversityDTO.cidade() != null && !updateUniversityDTO.cidade().isEmpty()){
            universitario.setMunicipio(updateUniversityDTO.cidade());
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
