package com.example.hestiaapipostgres.services;


import com.example.hestiaapipostgres.dto.InfoUserDTO;
import com.example.hestiaapipostgres.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Cacheable(value="cacheUserOrigin", key="#email")
    @CacheEvict(value = "cacheUserOrigin", key = "#email")
    public InfoUserDTO findUserOriginByEmail(String email){
        InfoUserDTO infoUserDTO = usuarioRepository.findUserOriginByEmail(email).orElseThrow(

                () -> new EntityNotFoundException("Este e-mail não existe em nenhuma das duas tabelas.")
        );
        System.out.println(infoUserDTO);
        return infoUserDTO;
    }
}
