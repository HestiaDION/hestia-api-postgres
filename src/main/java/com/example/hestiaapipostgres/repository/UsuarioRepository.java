package com.example.hestiaapipostgres.repository;

import com.example.hestiaapipostgres.dto.InfoUserDTO;
import com.example.hestiaapipostgres.models.Universitario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

// Para descobrir o tipo do usuário. O retorno é o e-mail e um campo criado chamado origem que pode ser anunciante ou universitário
public interface UsuarioRepository extends JpaRepository<Universitario, UUID> {


    @Query("SELECT new com.example.hestiaapipostgres.dto.InfoUserDTO(u.email, 'universitario') FROM Universitario u WHERE u.email = :email UNION SELECT new com.example.hestiaapipostgres.dto.InfoUserDTO(a.email, 'anunciante') FROM Anunciante a WHERE a.email = :email")
    Optional<InfoUserDTO> findUserOriginByEmail(@Param("email") String email);


}
