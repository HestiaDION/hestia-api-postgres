package com.example.hestiaapipostgres.services;

import com.example.hestiaapipostgres.dto.InfoUserDTO;
import com.example.hestiaapipostgres.dto.register.RegisterPagamentoDTO;
import com.example.hestiaapipostgres.models.Pagamento;
import com.example.hestiaapipostgres.models.Plano;
import com.example.hestiaapipostgres.repositories.PagamentoRepository;
import com.example.hestiaapipostgres.repositories.PlanoRepository;

import com.example.hestiaapipostgres.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PlanoRepository planoRepository;
    private final UsuarioRepository usuarioRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, PlanoRepository planoRepository, UsuarioRepository usuarioRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.planoRepository = planoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Pagamento registerPagamento(RegisterPagamentoDTO pagamentoDTO) {

        Optional<InfoUserDTO> infoUserOptional = usuarioRepository.findUserOriginByEmail(pagamentoDTO.emailUsuarioAssinante());
        if (infoUserOptional.isEmpty()) {
            throw new EntityNotFoundException("Este e-mail de usuário não existe");
        }

        Optional<Plano> planoOptional = planoRepository.findPlanoByUserEmail(pagamentoDTO.emailUsuarioAssinante());

        if (planoOptional.isPresent()) {
            Plano plano = planoOptional.get();

            Pagamento pagamento = new Pagamento(
                    pagamentoDTO.emailUsuarioAssinante(),
                    pagamentoDTO.nomeUsuarioAssinante(),
                    plano
            );

            return pagamentoRepository.save(pagamento);
        } else {
            throw new IllegalArgumentException("Plano não encontrado para o e-mail: " + pagamentoDTO.emailUsuarioAssinante());
        }
    }

}
