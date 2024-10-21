package com.example.hestiaapipostgres.services;

import com.example.hestiaapipostgres.dto.register.RegisterAnuncioDTO;
import com.example.hestiaapipostgres.models.Anuncio;
import com.example.hestiaapipostgres.repositories.AnuncianteRepository;
import com.example.hestiaapipostgres.repositories.AnuncioRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AnuncioService {

    private final AnuncioRepository anuncioRepository;
    private final AnuncianteRepository anuncianteRepository;

    public AnuncioService(AnuncioRepository anuncioRepository, AnuncianteRepository anuncianteRepository) {
        this.anuncioRepository = anuncioRepository;
        this.anuncianteRepository = anuncianteRepository;
    }

    // GET
    public List<Anuncio> listAllAds() {
        return anuncioRepository.findAll();
    }


    //  POST
    public Anuncio registerAd(RegisterAnuncioDTO registerAd) {

        // Data de registro da moradia
        LocalDate dataRegistro = LocalDate.now();

        // Data de término padrão: 1 mês após a data de registro
        LocalDate dataTermino = dataRegistro.plusDays(30);

        if(anuncianteRepository.findAnuncianteByEmail(registerAd.emailAnunciante()).isEmpty()){
            throw new EntityNotFoundException("Anunciante não encontrado.");
        }

        UUID anuncioId = anuncioRepository.addAnuncioImovelEndereco(
                registerAd.emailAnunciante(),
                registerAd.quantidadeMaximaPessoas(),
                dataRegistro,
                dataTermino,
                registerAd.nome(),
                registerAd.aluguel(),
                registerAd.descricao(),
                registerAd.regras(),
                registerAd.quantidadeQuartos(),
                registerAd.universidadeProxima(),
                registerAd.cep(),
                registerAd.cidade(),
                registerAd.bairro(),
                registerAd.rua(),
                registerAd.numero(),
                registerAd.complemento(),
                "SP"
        );

        return anuncioRepository.findAnuncioById(anuncioId)
                .orElseThrow(() -> new EntityExistsException("Erro ao inserir o anúncio"));
    }

}
