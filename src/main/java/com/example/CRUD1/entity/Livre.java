package com.example.CRUD1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titre;
    private int anneePublication;
    private Boolean disponibiliter;

    @ManyToOne
    @JoinColumn(name = "auteur_id")
    private Auteur auteur;

    //construteur vide

    public Livre() {

    }
    //constructeur complet

    public Livre(Long id, String titre , int anneePublication, Boolean disponibiliter,Auteur auteur) {
        this.id = id;
        this.titre = titre;
        this.anneePublication = anneePublication;
        this.disponibiliter = disponibiliter;
        this.auteur=auteur;
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
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
