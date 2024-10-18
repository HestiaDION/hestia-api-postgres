package com.example.hestiaapipostgres.services;


import com.example.hestiaapipostgres.dto.InfoUserDTO;
import com.example.hestiaapipostgres.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public InfoUserDTO findUserOriginByEmail(String email){
        InfoUserDTO infoUserDTO = usuarioRepository.findUserOriginByEmail(email).orElseThrow(

                () -> new EntityNotFoundException("Este e-mail não existe em nenhuma das duas tabelas.")
        );
        return infoUserDTO;
    }
}
