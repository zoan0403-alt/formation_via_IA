package com.example.CRUD1.gestionDeSalleDeSport.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Membre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String sexe;
    private int age;
    //plusieurs membres peuvent s'inscrire a plusieurs cours ManyToMany
    @ManyToMany()
    @JoinTable(name = "menbre_cours",
            joinColumns = @JoinColumn(name = "membre_id"),
            inverseJoinColumns = @JoinColumn(name="cours_id"))
    private List<Cours> mesCours;
    //un meme a au plus un abonnement
    @OneToOne(mappedBy = "membre")
    private Abonnement abonnement;

    public Membre() {
    }

    public Membre(Long id, String nom, String prenom, String sexe, int age, List<Cours> mescours, Abonnement abonnement) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.age = age;
        this.mesCours = mescours;
        this.abonnement = abonnement;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Cours> getMesCours() {
        return mesCours;
    }

    public void setMesCours(List<Cours> mesCours) {
        this.mesCours = mesCours;
    }

    public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }
}
