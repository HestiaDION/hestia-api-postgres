package com.example.hestiaapipostgres.controllers;


import com.example.hestiaapipostgres.models.Universitario;
import com.example.hestiaapipostgres.services.UniversitarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hestiaapi/postgres/university")
public class UniversitarioController {

    private final UniversitarioService universitarioService;

    public UniversitarioController(UniversitarioService universitarioService){
        this.universitarioService = universitarioService;
    }

    @GetMapping("findAll")
    public List<Universitario> findAllUniversities(){
        return universitarioService.listAllUniversities();
    }
}
