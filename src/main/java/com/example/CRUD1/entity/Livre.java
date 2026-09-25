package com.example.CRUD1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titre;
    private String auteur;
    private int anneePublication;
    private Boolean disponibiliter;

    //construteur vide

    public Livre() {

    }
    //constructeur complet

    public Livre(Long id, String titre, String auteur, int anneePublication, Boolean disponibiliter) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.disponibiliter = disponibiliter;
    }

    //getters and setters

    public Long getId() {
        return id;
    }
    @NotBlank(message = "Le titre ne doit pas etre vide")
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
