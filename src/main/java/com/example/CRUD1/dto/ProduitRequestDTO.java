package com.example.CRUD1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProduitRequestDTO {

    @NotBlank(message = "le nom du produit ne doit etre vide.")
    private String nom;
    @Positive(message = "Le prix d'un produit ne peut etre negatif")
    private double prix;

    public ProduitRequestDTO() {

    }

    public ProduitRequestDTO(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
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

