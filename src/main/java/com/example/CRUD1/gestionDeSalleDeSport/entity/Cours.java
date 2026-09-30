package com.example.CRUD1.gestionDeSalleDeSport.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String nom;
    private int duree; //en jours
    //pluisieurs cours sont dispensees par le meme prof @ManyToOne
    @ManyToOne()
    @JoinColumn(name = "coach_id")
    private Coach coach;
    //un cours est souscrire par un ou plusieurs membres @ManyToMay
    @ManyToMany(mappedBy = "mesCours")
    private List<Membre> participants;

    public Cours() {
    }

    public Cours(Long id, String nom, int duree, Coach coach, List<Membre> participants) {
        Id = id;
        this.nom = nom;
        this.duree = duree;
        this.coach = coach;
        this.participants = participants;
    }

    public Long getId() {
        return Id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public List<Membre> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Membre> participants) {
        this.participants = participants;
    }
}
