//package com.example.hestiaapipostgres.controllers;
//
//
//import com.example.hestiaapipostgres.dto.register.RegisterPagamentoDTO;
//import com.example.hestiaapipostgres.exceptions.CustomErrorResponse;
//import com.example.hestiaapipostgres.models.Pagamento;
//import com.example.hestiaapipostgres.services.PagamentoService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/payment")
//public class PagamentoController {
//
//    private final PagamentoService pagamentoService;
//
//     public PagamentoController(PagamentoService pagamentoService){
//         this.pagamentoService = pagamentoService;
//     }
//
////                  =-=-==-=--=-= POST ---==-=--=
//    @PostMapping("registerPayment")
//    @Operation(summary = "Registro de um pagamento",
//            description = "Registra um pagamento no banco de dados")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Pagamento cadastrado com suceso. Retorna o pagamento cadastrado.",
//                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterPagamentoDTO.class))),
//            @ApiResponse(responseCode = "400", description = "Erro no body da requisição",
//                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))),
//            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
//                    content = @Content())
//    })
//    public ResponseEntity<Pagamento> registerPagamento(@Valid @RequestBody RegisterPagamentoDTO registerPagamentoDTO){
//
//        Pagamento pagamento = pagamentoService.registerPagamento(registerPagamentoDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);
//
//    }
//
//}
