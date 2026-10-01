package com.example.CRUD1.gestionDeSalleDeSport.dto;

public class CoachSimpleReponseDTO {
    private Long id;
    private String nom;
    private String prenom;

    public CoachSimpleReponseDTO() {
    }

    public CoachSimpleReponseDTO(Long id, String nom, String prenom) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Long getId() {
        return id;
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
