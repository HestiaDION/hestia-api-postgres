package com.example.hestiaapipostgres.services;

import com.example.hestiaapipostgres.dto.InfoUserDTO;
import com.example.hestiaapipostgres.dto.TokenAccess;
import com.example.hestiaapipostgres.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class JwtTokenService {

    private final JwtEncoder jwtEncoder;
    private final UsuarioRepository usuarioRepository;

    private final String ADMIN_PERMISSION = "ADMIN";

    public JwtTokenService(JwtEncoder jwtEncoder, UsuarioRepository usuarioRepository) {
        this.jwtEncoder = jwtEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    public TokenAccess generateToken(String email) throws AccessDeniedException {
        Instant now = Instant.now();
        Optional<InfoUserDTO> infoUserDTO = usuarioRepository.findUserOriginByEmail(email);

        if (infoUserDTO.isEmpty()){
            throw new EntityNotFoundException("Usuário não encontrado em nenhuma das tabelas");
        }

        String tipoUsuario = infoUserDTO.get().tipoConta();
        System.out.println(tipoUsuario);
        if (!tipoUsuario.equals("ADMIN")){
            throw new AccessDeniedException("Você não possui permissão para acessar este recurso");
        }


        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(email)
                .claim("scope", ADMIN_PERMISSION)
                .build();

        JwsHeader jwsHeader = JwsHeader.with(() -> "RS256").build();

        String token = this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claimsSet)).getTokenValue();
        return new TokenAccess(token);

    }
}