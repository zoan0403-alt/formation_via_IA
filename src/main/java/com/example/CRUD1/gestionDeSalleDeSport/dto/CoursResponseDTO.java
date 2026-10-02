package com.example.CRUD1.gestionDeSalleDeSport.dto;

import com.example.CRUD1.gestionDeSalleDeSport.entity.Coach;
import com.example.CRUD1.gestionDeSalleDeSport.entity.Membre;

import java.util.List;

public class CoursResponseDTO {
    private Long Id;
    private String nom;
    private int duree;
    private CoachSimpleReponseDTO coach;
    // je me dis que c'est pas important de mettre ici la liste des membre

    public CoursResponseDTO() {
    }

    public CoursResponseDTO(Long id, String nom, int duree, CoachSimpleReponseDTO coach) {
        Id = id;
        this.nom = nom;
        this.duree = duree;
        this.coach = coach;
    }

    public Long getId() {
        return Id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public CoachSimpleReponseDTO getCoach() {
        return coach;
    }

    public void setCoach(CoachSimpleReponseDTO coach) {
        this.coach = coach;
    }
}
