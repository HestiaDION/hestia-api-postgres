package com.example.hestiaapipostgres.controllers;


import com.example.hestiaapipostgres.dto.TokenAccess;
import com.example.hestiaapipostgres.services.JwtTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/token")
public class JwtTokenController {
    private final JwtTokenService tokenService;

    public JwtTokenController(JwtTokenService tokenService){
        this.tokenService = tokenService;
    }

    @GetMapping("/access/{email}")
    public ResponseEntity<TokenAccess> getAccessToken(@PathVariable String email){

        try{
            return ResponseEntity.ok().body(tokenService.generateToken(email));
        }catch (AccessDeniedException ade){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}