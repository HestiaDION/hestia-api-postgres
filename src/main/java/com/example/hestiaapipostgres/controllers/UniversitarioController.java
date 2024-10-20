package com.example.hestiaapipostgres.controllers;

import com.example.hestiaapipostgres.dto.perfil.AnuncianteProfileInfo;
import com.example.hestiaapipostgres.dto.register.RegisterAdvertiserDTO;
import com.example.hestiaapipostgres.dto.register.RegisterUniversityDTO;

import com.example.hestiaapipostgres.dto.perfil.UniversitarioProfileInfo;
import com.example.hestiaapipostgres.dto.update.UpdateAdvertiserDTO;
import com.example.hestiaapipostgres.dto.update.UpdateUniversityDTO;
import com.example.hestiaapipostgres.exceptions.CustomErrorResponse;
import com.example.hestiaapipostgres.models.Universitario;
import com.example.hestiaapipostgres.services.UniversitarioService;

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
@RequestMapping("/university")
public class UniversitarioController {

    private final UniversitarioService universitarioService;

    public UniversitarioController(UniversitarioService universitarioService){
        this.universitarioService = universitarioService;
    }

    //                        ==--=--= GETS -==-=-=-=-

    @GetMapping("/listAll")
    @Operation(summary = "Listagem de todos os universitários",
            description = "Listagem completa de todos os universitários registrados na base, com todas as suas informações.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de universitários retornada com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Universitario.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public List<Universitario> findAllUniversities(){
        return universitarioService.listAllUniversities();
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Listagem de perfil de universitário por UUID",
            description = "Lista as informações de perfil de um universitário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações de perfil retornadas com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UniversitarioProfileInfo.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Universitário não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public Universitario findUniversityById(@PathVariable UUID id){
       return universitarioService.listUniversityById(id);
    }

    @GetMapping("/profile/{email}")
    @Operation(summary = "Listagem de perfil de universitário por e-mail",
            description = "Lista as informações de perfil de um universitário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações de perfil retornadas com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UniversitarioProfileInfo.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Universitário não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public ResponseEntity<UniversitarioProfileInfo> getProfileByUniversity(@PathVariable String email){
        UniversitarioProfileInfo universitarioProfileInfo = universitarioService.getInfoProfileByUniversity(email);
        return ResponseEntity.ok().body(universitarioProfileInfo);
    }

    //                        =-=-=-= POSTS =-=-=-=

    @PostMapping("/register")
    @Operation(summary = "Registro um universitário",
            description = "Registra um universitário no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Universitário registrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterAdvertiserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro no body da requisição",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public ResponseEntity<Universitario> registerUniversity(@Valid @RequestBody RegisterUniversityDTO registerUniversityDTO){

        Universitario universitario = universitarioService.registerUniversity(registerUniversityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(universitario);

    }


    //                   =--==--===- PATCH -==-=-=-=-=-

    @PatchMapping("/updateProfile/{email}")
    @Operation(summary = "Atualização do perfil de um universitário",
            description = "Atualiza algumas informações mapeadas do perfil do universitário. Retorna o universitário atualizado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Universitário atualizado com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateUniversityDTO.class))),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado na base.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Erro no body ou URL da requisição",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public ResponseEntity<Universitario> updateUniversity(@PathVariable String email, @Valid @RequestBody UpdateUniversityDTO updateUniversityDTO){

        Universitario universitario = universitarioService.updateUniversity(email, updateUniversityDTO);

        return ResponseEntity.status(HttpStatus.OK).body(universitario);
    }

}
