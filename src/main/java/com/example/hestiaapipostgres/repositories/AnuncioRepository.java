package com.example.hestiaapipostgres.repositories;

import com.example.hestiaapipostgres.models.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository

public interface AnuncioRepository extends JpaRepository<Anuncio, UUID> {

    @Query(value = "SELECT add_anuncio_imovel_endereco( " +
            "CAST(:emailAnunciante AS TEXT), " +
            "CAST(:quantidadeMaximaPessoas AS INTEGER), " +
            "CAST(:dtInicio AS DATE), " +
            "CAST(:dtTermino AS DATE), " +
            "CAST(:nomeAnuncio AS VARCHAR), " +
            "CAST(:aluguel AS NUMERIC), " +
            "CAST(:descricaoImovel AS TEXT), " +
            "CAST(:regrasImovel AS TEXT), " +
            "CAST(:quantidadeQuartos AS INTEGER), " +
            "CAST(:universidadeProxima AS VARCHAR), " +
            "CAST(:cep AS VARCHAR), " +
            "CAST(:cidade AS VARCHAR), " +
            "CAST(:bairro AS VARCHAR), " +
            "CAST(:rua AS VARCHAR), " +
            "CAST(:numero AS VARCHAR), " +
            "CAST(:complemento AS VARCHAR), " +
            "CAST(:uf AS VARCHAR))", nativeQuery = true)
    UUID addAnuncioImovelEndereco(
            @Param("emailAnunciante") String emailAnunciante,
            @Param("quantidadeMaximaPessoas") int quantidadeMaximaPessoas,
            @Param("dtInicio") LocalDate dtInicio,
            @Param("dtTermino") LocalDate dtTermino,
            @Param("nomeAnuncio") String nomeAnuncio,
            @Param("aluguel") double aluguel,
            @Param("descricaoImovel") String descricaoImovel,
            @Param("regrasImovel") String regrasImovel,
            @Param("quantidadeQuartos") int quantidadeQuartos,
            @Param("universidadeProxima") String universidadeProxima,
            @Param("cep") String cep,
            @Param("cidade") String cidade,
            @Param("bairro") String bairro,
            @Param("rua") String rua,
            @Param("numero") String numero,
            @Param("complemento") String complemento,
            @Param("uf") String uf
    );


    Optional<Anuncio> findAnuncioById(UUID id);


}
