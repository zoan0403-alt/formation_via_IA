package com.example.CRUD1.gestionDeSalleDeSport.dto;

import com.example.CRUD1.gestionDeSalleDeSport.entity.Coach;
import com.example.CRUD1.gestionDeSalleDeSport.entity.Membre;

import java.util.List;

public class CoursResponseDTO {
    private Long Id;
    private String nom;
    private int duree;
    private CoachSimpleReponseDTO coach;
}
