package com.example.CRUD1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class AuteurRequestDTO {

    @NotBlank(message = "Le nom de l'auteur ne peut etre vide")
    private String nom;
    @NotBlank(message = "la nationalite de l'auteur est obligatoire")
    private String nationalite;
    @NotNull(message = "La date de naissance de l'auteur est obligatoire")
    private Date dateNaissance;

    public AuteurRequestDTO() {
    }

    public AuteurRequestDTO(String nom, String nationalite, Date dateNaissance) {
        this.nom = nom;
        this.nationalite = nationalite;
        this.dateNaissance = dateNaissance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}
