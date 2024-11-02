package com.example.hestiaapipostgres.controllers;

import com.example.hestiaapipostgres.dto.get.ImovelAnuncioDTO;
import com.example.hestiaapipostgres.dto.perfil.AnuncianteProfileInfo;
import com.example.hestiaapipostgres.dto.perfil.UniversitarioProfileInfo;
import com.example.hestiaapipostgres.dto.register.RegisterAdvertiserDTO;
import com.example.hestiaapipostgres.dto.register.RegisterAnuncioDTO;
import com.example.hestiaapipostgres.exceptions.CustomErrorResponse;
import com.example.hestiaapipostgres.models.Anuncio;
import com.example.hestiaapipostgres.models.Imovel;
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
import java.util.UUID;

@RestController
@RequestMapping("/ad")
public class AnuncioController {

    private final AnuncioService anuncioService;

    public AnuncioController(AnuncioService anuncioService){
        this.anuncioService = anuncioService;
    }

    //        =-=--=-==--==-=--=-=-==--==-=-=-=- GET -==--==--===--=-==-=--==-=-=-=-=--==-=--=-=
    @GetMapping("/property/listAllByAdvertiser/{email}")
    @Operation(summary = "Listagem de imóveis com informações adicionais pelo e-mail do anunciante",
            description = "Retorna uma lista com todos os imóveis com informações adicionais pelo e-mail do anunciante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de imóveis com informações adicionais pelo e-mail do anunciante retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UniversitarioProfileInfo.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Universitário não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public List<ImovelAnuncioDTO> getImoveisAnunciosPorAnuncianteEmail(@PathVariable("email") String emailAnunciante){
        return anuncioService.listAdsPropertiesByAdvertiserEmail(emailAnunciante);
    }

    @GetMapping("/property/getPropertyById/{id}")
    @Operation(summary = "Get de imóvel com informações adicionais por seu ID",
            description = "Retorna um imóvel com informações adicionais com base em seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imóvel retornado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UniversitarioProfileInfo.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Imóvel não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public ResponseEntity<ImovelAnuncioDTO> getPropertyById(@PathVariable() UUID id){
        return ResponseEntity.ok().body(anuncioService.getAdPropertyByImovelId(id));
    }

    @GetMapping("/property/listAll")
    @Operation(summary = "Listagem de todos os imóveis (quando se clica em 'Mais informações')",
            description = "Listagem completa de todos os imóveis com informações adicionais.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de imóveis retornada com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImovelAnuncioDTO.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public List<ImovelAnuncioDTO> getImoveisComAnuncios() {
        return anuncioService.listAllAdsProperties();
    }

    @GetMapping("/listAll")
//    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @Operation(summary = "Listagem de todos os anúncios",
            description = "Listagem completa de todos os anúncios cadastrados na base, independente de seu anunciante.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de anúncios retornada com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Universitario.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public List<Anuncio> findAllAds(){
        return anuncioService.listAllAds();
    }


    //        =-=--=-==--==-=--=-=-==--==-=-=-=- POST -==--==--===--=-==-=--==-=-=-=-=--==-=--=-=

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
