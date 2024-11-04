package com.example.hestiaapipostgres.services;

import com.example.hestiaapipostgres.dto.register.RegisterUniversityDTO;
import com.example.hestiaapipostgres.dto.perfil.UniversitarioProfileInfo;
import com.example.hestiaapipostgres.dto.update.UpdateUniversityDTO;
import com.example.hestiaapipostgres.models.Universitario;
import com.example.hestiaapipostgres.repositories.UniversitarioRepository;
import jakarta.persistence.EntityExistsException;

import jakarta.persistence.EntityNotFoundException;
import org.postgresql.util.PGobject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UniversitarioService {

    private final UniversitarioRepository universitarioRepository;
    private final String USER_TYPE_DEFAULT = "USER";
    private final String DEFAULT_PREFIX = "55";

    public UniversitarioService(UniversitarioRepository universitarioRepository){
        this.universitarioRepository = universitarioRepository;
    }

    public List<Universitario> listAllUniversities(){
        return universitarioRepository.findAll();
    }

    @Cacheable(value = "cacheUniversityProfile", key="#email")
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

    public UUID get_user_uuid_by_email(String email) {
        Object result = universitarioRepository.get_user_uuid_by_email(email);
        String uuidString = null;
        if (result instanceof PGobject) {
            String value = ((PGobject) result).getValue();
            System.out.println("Valor retornado: " + value); // Log do valor retornado

            // Divide a string usando a vírgula como delimitador
            String[] parts = value.split(",");

            // Verifica se há pelo menos duas partes e obtém o UUID
            if (parts.length > 1) {
                uuidString = parts[1].replace(")", "").strip();
                System.out.println("UUID: " + uuidString);
            } else {
                System.out.println("UUID não encontrado.");
            }

            // Verifica se o valor é um UUID válido antes de converter
            if (uuidString != null) {
                return UUID.fromString(uuidString);
            } else {
                throw new IllegalArgumentException("O valor retornado não é um UUID válido: " + value);
            }
        }

        throw new EntityNotFoundException("UUID do universitário não encontrado ou não é um PGobject");
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
                DEFAULT_PREFIX,
                registerUniversityDTO.telefone(),
                registerUniversityDTO.universidade(),
                "",
                USER_TYPE_DEFAULT

        );

        // Retorna o universitário registrado
        return universitarioRepository.findUniversitarioByEmail(registerUniversityDTO.email())
                .orElseThrow(() -> new EntityExistsException("Erro ao inserir o universitário"));
    }


    // PATCH
    @CacheEvict(value = "cacheUniversityProfile", key= "#email")
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
