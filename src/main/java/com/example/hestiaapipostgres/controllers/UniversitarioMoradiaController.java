package com.example.hestiaapipostgres.controllers;


import com.example.hestiaapipostgres.dto.perfil.UniversitarioProfileInfo;
import com.example.hestiaapipostgres.dto.register.RegisterAdvertiserDTO;

import com.example.hestiaapipostgres.dto.register.RegisterUniversityPropertyDTO;
import com.example.hestiaapipostgres.exceptions.CustomErrorResponse;

import com.example.hestiaapipostgres.models.Universitario;
import com.example.hestiaapipostgres.models.UniversitarioMoradia;
import com.example.hestiaapipostgres.services.UniversitarioMoradiaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/property")
public class UniversitarioMoradiaController {

    private final UniversitarioMoradiaService universitarioMoradiaService;

    public UniversitarioMoradiaController(UniversitarioMoradiaService universitarioMoradiaService){
        this.universitarioMoradiaService = universitarioMoradiaService;
    }

    @PostMapping("/joinProperty")
    @Operation(summary = "Registro de um universitário em moradia",
            description = "Registra um universitário ID e um imóvel ID na tabela de relação entre ambos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro efetuado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterAdvertiserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro no body da requisição",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public ResponseEntity<UniversitarioMoradia> joinProperty(@Valid @RequestBody RegisterUniversityPropertyDTO registerUniversityPropertyDTO){
        UniversitarioMoradia universitarioMoradia = universitarioMoradiaService.entrarEmUmaMoradia(registerUniversityPropertyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(universitarioMoradia);

    }

    @GetMapping("/listUniversitiesByImovelId/{imovelId}")
    public List<Universitario> listUniversitiesByImovelId(@PathVariable UUID imovelId){
        return universitarioMoradiaService.listUniversitariosByImovelId(imovelId);
    }
}
