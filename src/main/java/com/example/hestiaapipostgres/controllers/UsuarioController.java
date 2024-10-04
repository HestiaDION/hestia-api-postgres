package com.example.hestiaapipostgres.controllers;

import com.example.hestiaapipostgres.dto.InfoUserDTO;
import com.example.hestiaapipostgres.repository.UsuarioRepository;
import com.example.hestiaapipostgres.services.UsuarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    private final UsuarioService usuarioService;
     public UsuarioController(UsuarioService usuarioService){
         this.usuarioService = usuarioService;
     }

     @GetMapping("/{email}")
     public ResponseEntity<InfoUserDTO> findUserOriginByEmail(@PathVariable String email){
         return ResponseEntity.ok().body(usuarioService.findUserOriginByEmail(email));
     }
}
