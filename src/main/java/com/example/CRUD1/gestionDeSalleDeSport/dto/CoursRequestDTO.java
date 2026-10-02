package com.example.CRUD1.gestionDeSalleDeSport.dto;

import com.example.CRUD1.gestionDeSalleDeSport.entity.Coach;
import com.example.CRUD1.gestionDeSalleDeSport.entity.Membre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public class CoursRequestDTO {
    @NotBlank(message = "le nom du cours de peut etre vide")
    @Size(min=4,max = 50,message = "le nom doit avoir etre 4 et 50 caracteres")
    private String nom;
    @Positive(message = "la duree ne peut etre negative")
    private int duree;
    private Long coachId;

    public CoursRequestDTO() {
    }

    public CoursRequestDTO(String nom, int duree, Long coachId) {
        this.nom = nom;
        this.duree = duree;
        this.coachId = coachId;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
