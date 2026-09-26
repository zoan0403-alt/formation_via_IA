package com.example.CRUD1.dto;

public class ProduitResponseDTO {
    private Long id;
    private String nom;
    private double prix;


    public ProduitResponseDTO() {
    }

    public ProduitResponseDTO(Long id, String nom, double prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }


}
