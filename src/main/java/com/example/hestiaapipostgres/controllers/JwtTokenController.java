package com.example.hestiaapipostgres.controllers;


import com.example.hestiaapipostgres.dto.TokenAccess;
import com.example.hestiaapipostgres.services.JwtTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/token")
public class JwtTokenController {
    private final JwtTokenService tokenService;

    public JwtTokenController(JwtTokenService tokenService){
        this.tokenService = tokenService;
    }

    @GetMapping("/access/{email}")
    public ResponseEntity<TokenAccess> getAccessToken(@PathVariable String email){
        return ResponseEntity.ok().body(tokenService.generateToken(email));
    }
}
