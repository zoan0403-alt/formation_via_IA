package com.example.CRUD1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class LivreRequestDTO {
    @NotBlank(message = "Le titre ne peut etre vide.")
    private String titre;
    @NotBlank(message = "le nom de l'auteur est obligatoire")
    private String auteur;
    @Positive(message = "l'annees de piblication doit etre nom vide et possitif")
    private int anneePublication;
    //il peut ne pas fournie et par defaut on mets le livre comme disponible (true) voir service
    private Boolean disponibiliter;

    public LivreRequestDTO() {}

    public LivreRequestDTO(String titre, String auteur, int anneePublication, Boolean disponibiliter) {
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.disponibiliter = disponibiliter;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }

    public Boolean getDisponibiliter() {
        return disponibiliter;
    }

    public void setDisponibiliter(Boolean disponibiliter) {
        this.disponibiliter = disponibiliter;
    }
}
