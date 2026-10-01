package com.example.CRUD1.gestionDeSalleDeSport.controller;

import com.example.CRUD1.gestionDeSalleDeSport.service.CoursService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cours")
public class CoursController {
    private final CoursService coursService;

    public CoursController(CoursService coursService) {
        this.coursService = coursService;
    }
    //ajouter un cours
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public
}
