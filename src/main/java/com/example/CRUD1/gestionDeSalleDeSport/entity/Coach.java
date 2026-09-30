package com.example.CRUD1.gestionDeSalleDeSport.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    //un coach donne un ou plusieurs cours @OneToMany avec
    @OneToMany(mappedBy = "coach")
    private List<Cours> mesCours;

    public Coach() {
    }

    public Coach(Long id, String nom, String prenom, List<Cours> mesCours) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mesCours = mesCours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Cours> getMesCours() {
        return mesCours;
    }

    public void setMesCours(List<Cours> mesCours) {
        this.mesCours = mesCours;
    }
}
