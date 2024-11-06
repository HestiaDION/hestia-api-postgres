package com.example.hestiaapipostgres.repositories;

import com.example.hestiaapipostgres.models.Anuncio;
import com.example.hestiaapipostgres.models.Universitario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.example.hestiaapipostgres.dto.get.ImovelAnuncioDTO;

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

    @Query("SELECT new com.example.hestiaapipostgres.dto.get.ImovelAnuncioDTO(i.id, i.regras, i.descricao, i.quantidadeQuartos, " +
            "i.universidadeProxima, i.quantidadeMaximaPessoas, a.nome, a.aluguel, a.dt_inicio, a.anunciante.email, a.anunciante.id) " +
            "FROM Anuncio a JOIN Imovel i ON a.imovel.id = i.id")
    List<ImovelAnuncioDTO> findAllImoveisComAnuncios();

//    @Query("SELECT new com.example.hestiaapipostgres.dto.get.ImovelAnuncioDTO(i.id, i.regras, i.descricao, i.quantidadeQuartos, " +
//            "i.universidadeProxima, i.quantidadeMaximaPessoas, a.nome, a.aluguel, a.dt_inicio) " +
//            "FROM Anuncio a JOIN Imovel i ON a.imovel_id = i.id WHERE a.anunciante.id = :anuncianteId")
//    Optional<ImovelAnuncioDTO> findAnuncioImovelByAnuncianteId(@Param("anuncianteId") UUID anuncianteId);

    @Query("SELECT DISTINCT new com.example.hestiaapipostgres.dto.get.ImovelAnuncioDTO(a.id, i.regras, i.descricao, i.quantidadeQuartos, " +
            "i.universidadeProxima, i.quantidadeMaximaPessoas, a.nome, a.aluguel, a.dt_inicio, a.anunciante.email, a.anunciante.id) " +
            "FROM Anuncio a JOIN a.imovel i WHERE a.anunciante.email = :email")
    List<ImovelAnuncioDTO> findAnunciosImovelByAnuncianteEmail(@Param("email") String emailAnunciante);


    @Query("SELECT DISTINCT new com.example.hestiaapipostgres.dto.get.ImovelAnuncioDTO(i.id, i.regras, i.descricao, i.quantidadeQuartos, " +
            "i.universidadeProxima, i.quantidadeMaximaPessoas, a.nome, a.aluguel, a.dt_inicio, a.anunciante.email, a.anunciante.id) " +
            "FROM Anuncio a JOIN Imovel i ON i.id = a.imovel.id WHERE a.imovel.id = :imovelId")
    Optional<ImovelAnuncioDTO> findAnuncioByImovelId(@Param("imovelId") UUID imovelId);

    @Query("SELECT DISTINCT new com.example.hestiaapipostgres.dto.get.ImovelAnuncioDTO(i.id, i.regras, i.descricao, i.quantidadeQuartos, " +
            "i.universidadeProxima, i.quantidadeMaximaPessoas, a.nome, a.aluguel, a.dt_inicio, a.anunciante.email, a.anunciante.id) " +
            "FROM Anuncio a JOIN Imovel i ON i.anuncio.id = a.id WHERE a.id = :anuncioId")
    Optional<ImovelAnuncioDTO> findAnuncioByAnuncioId(@Param("anuncioId") UUID anuncioId);

    Optional<Anuncio> findAnuncioById(UUID id);





}
