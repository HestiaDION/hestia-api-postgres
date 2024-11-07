package com.example.hestiaapipostgres.controllers;


import com.example.hestiaapipostgres.dto.perfil.AnuncianteProfileInfo;
import com.example.hestiaapipostgres.dto.perfil.UniversitarioProfileInfo;
import com.example.hestiaapipostgres.dto.register.RegisterAdvertiserDTO;
import com.example.hestiaapipostgres.dto.update.UpdateAdvertiserDTO;
import com.example.hestiaapipostgres.exceptions.CustomErrorResponse;
import com.example.hestiaapipostgres.models.Anunciante;

import com.example.hestiaapipostgres.models.Universitario;
import com.example.hestiaapipostgres.services.AnuncianteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/advertiser")

public class AnuncianteController {
    private final AnuncianteService anuncianteService;

    public AnuncianteController(AnuncianteService anuncianteService){
        this.anuncianteService = anuncianteService;
    }


    //          =-=-=- GETS =-=-=--=

    @GetMapping("/find/{id}")
    @Operation(summary = "Listagem de perfil de anunciante por UUID",
            description = "Lista as informações de perfil de um anunciante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações de perfil retornadas com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UniversitarioProfileInfo.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Universitário não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public Anunciante findAdvertiserById(@PathVariable UUID id){
        return anuncianteService.listAdvertiserById(id);
    }

    @GetMapping("listAll")

    @Operation(summary = "Listagem de todos os anunciantes",
            description = "Listagem completa de todos os anunciantes registrados na base, com todas as suas informações.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de anunciantes retornada com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Universitario.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public List<Anunciante> findAllAdvertisers(){
        return anuncianteService.listAllAdvertisers();
    }

    @GetMapping("/profile/{email}")
    @Operation(summary = "Listagem de perfil de anunciante por e-mail",
            description = "Lista as informações de perfil de um anunciante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações de perfil retornadas com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AnuncianteProfileInfo.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Anunciante não encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public ResponseEntity<AnuncianteProfileInfo> getProfileByUniversity(@PathVariable String email){

        AnuncianteProfileInfo anuncianteProfileInfo = anuncianteService.getInfoProfileByAdvertiser(email);
        return ResponseEntity.ok().body(anuncianteProfileInfo);
    }



    //            =--=-= POSTS -=--=-=-

    @PostMapping("/register")
    @Operation(summary = "Registro um anunciante",
            description = "Registra um an'unciante no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Anunciante registrado com sucesso. Retorna o anunciante registrado.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterAdvertiserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro no body da requisição",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public ResponseEntity<Anunciante> registerAdvertiser(@Valid @RequestBody RegisterAdvertiserDTO registerAdvertiserDTO){

        Anunciante anunciante = anuncianteService.registerAdvertiser(registerAdvertiserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(anunciante);

    }


    //         =-=-=-= PATCH =-=-==-
    @PatchMapping("/updateProfile/{email}")
    @Operation(summary = "Atualização do perfil de um anunciante",
            description = "Atualiza algumas informações mapeadas do perfil do anunciante. Retorna o anunciante atualizado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anunciante atualizado com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateAdvertiserDTO.class))),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado na base.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Erro no body ou URL da requisição",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public ResponseEntity<Anunciante> updateAdvertiser(@PathVariable String email, @Valid @RequestBody UpdateAdvertiserDTO updateAdvertiserDTO){

        Anunciante anunciante = anuncianteService.updateAdvertiser(email, updateAdvertiserDTO);
        return ResponseEntity.status(HttpStatus.OK).body(anunciante);
    }

}
