package com.example.CRUD1.entity;

import jakarta.persistence.*;

@Entity
public class ProfilAuteur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bibliogaphie;
    private String photoURL;
    private String reseauSociaux;

    @OneToOne()
    @JoinColumn(name="auteur_id")
    private Auteur auteur;
    public ProfilAuteur(){

    }

    public ProfilAuteur(Long id, String bibliogaphie, String photoURL, String reseauSociaux,Auteur auteur) {
        this.id = id;
        this.bibliogaphie = bibliogaphie;
        this.photoURL = photoURL;
        this.reseauSociaux = reseauSociaux;
        this.auteur=auteur;
    }

    public Long getId() {
        return id;
    }

    public String getBibliogaphie() {
        return bibliogaphie;
    }

    public void setBibliogaphie(String bibliogaphie) {
        this.bibliogaphie = bibliogaphie;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getReseauSociaux() {
        return reseauSociaux;
    }

    public void setReseauSociaux(String reseauSociaux) {
        this.reseauSociaux = reseauSociaux;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }
}
