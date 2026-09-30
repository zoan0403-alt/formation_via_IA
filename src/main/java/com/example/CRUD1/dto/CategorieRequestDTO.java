package com.example.CRUD1.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class CategorieRequestDTO {

    @NotBlank(message = "le nom de la categorie ne peut etre vide")
    @Length(min = 5,max = 30,message = "le nom contenir entre 5 et 30 caracteres")
    private String nom;
    @NotBlank(message="La description ne doit etre  vide")
    @Length(min=10,max = 150,message = "la description doit tenir entre 30 et 150 caractere")
    private String description;

    public CategorieRequestDTO() {
    }

    public CategorieRequestDTO(String nom, String description) {
        this.nom = nom;
        this.description = description;
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
