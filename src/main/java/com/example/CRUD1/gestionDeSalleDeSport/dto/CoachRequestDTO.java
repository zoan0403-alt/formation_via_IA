package com.example.CRUD1.gestionDeSalleDeSport.dto;

import jakarta.validation.constraints.NotBlank;

public class CoachRequestDTO {
    @NotBlank(message = "le nom du coach ne peut etre vide")
    private String nom;
    @NotBlank(message = "le prenom du coach ne peut etre vide")
    private String prenom;

    public CoachRequestDTO() {
    }

    public CoachRequestDTO(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
