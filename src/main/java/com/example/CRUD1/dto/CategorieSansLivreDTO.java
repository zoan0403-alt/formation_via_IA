package com.example.CRUD1.dto;

public class CategorieSansLivreDTO {
    private Long id;
    private String nom;
    private String description;

    public CategorieSansLivreDTO() {
    }

    public CategorieSansLivreDTO(Long id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
