package com.example.CRUD1.entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Auteur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String nationalite;
    private Date dateNaissance;

    @OneToMany(mappedBy = "auteur")
    private List<Livre> mesLivres;
    @OneToOne(mappedBy = "auteur")
    private ProfilAuteur profilAuteur;
    public Auteur() {    }

    public Auteur(Long id, String nom, String nationalite, Date dateNaissance ) {
        this.id = id;
        this.nom = nom;
        this.nationalite = nationalite;
        this.dateNaissance = dateNaissance;
        this.mesLivres = new ArrayList<>();
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

    public List<Livre> getMesLivres() {
        return mesLivres;
    }

    public void setMesLivres(List<Livre> mesLivres) {
        this.mesLivres = mesLivres;
    }

    public ProfilAuteur getProfilAuteur() {
        return profilAuteur;
    }

    public void setProfilAuteur(ProfilAuteur profilAuteur) {
        this.profilAuteur = profilAuteur;
    }
}
