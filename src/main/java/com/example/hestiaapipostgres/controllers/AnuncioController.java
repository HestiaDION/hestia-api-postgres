package com.example.hestiaapipostgres.controllers;

import com.example.hestiaapipostgres.dto.register.RegisterAdvertiserDTO;
import com.example.hestiaapipostgres.dto.register.RegisterAnuncioDTO;
import com.example.hestiaapipostgres.exceptions.CustomErrorResponse;
import com.example.hestiaapipostgres.models.Anuncio;
import com.example.hestiaapipostgres.models.Universitario;
import com.example.hestiaapipostgres.services.AnuncioService;
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

@RestController
@RequestMapping("/ad")
public class AnuncioController {

    private final AnuncioService anuncioService;

    public AnuncioController(AnuncioService anuncioService){
        this.anuncioService = anuncioService;
    }

    //        =-=-=-=- GET -==--==-

    @GetMapping("/listAll")
//    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @Operation(summary = "Listagem de todos os anúncios",
            description = "Listagem completa de todos os anúncios cadastrados na base, independente de seu anunciante.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de anúncios retornada com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Anuncio.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public List<Anuncio> findAllAds(){
        return anuncioService.listAllAds();
    }


    //       =--==-=-=- POST =--=--==

    @PostMapping("/register")
    @Operation(summary = "Registra um anúncio (moradia)",
            description = "Registra um moradia no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Moradia cadastrada com sucesso. Retorna a moradia registrada.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterAdvertiserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro no body da requisição",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public ResponseEntity<Anuncio> registerAd(@Valid @RequestBody RegisterAnuncioDTO registerAnuncioDTO){

        Anuncio anuncio = anuncioService.registerAd(registerAnuncioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(anuncio);

    }

}
