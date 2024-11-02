package com.example.hestiaapipostgres.services;

import com.example.hestiaapipostgres.dto.register.RegisterPagamentoDTO;
import com.example.hestiaapipostgres.models.Pagamento;
import com.example.hestiaapipostgres.models.Plano;
import com.example.hestiaapipostgres.repositories.PagamentoRepository;
import com.example.hestiaapipostgres.repositories.PlanoRepository;

import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PlanoRepository planoRepository;


    public PagamentoService(PagamentoRepository pagamentoRepository, PlanoRepository planoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.planoRepository = planoRepository;
    }

    public Pagamento registerPagamento(RegisterPagamentoDTO pagamentoDTO) {
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
            throw new IllegalArgumentException("Plano não encontrado com ID: " + planoOptional.get().getId());
        }

    }
}
