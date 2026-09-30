package com.example.CRUD1.gestionDeSalleDeSport.entity;

import jakarta.persistence.*;

@Entity
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private String dateDebut;
    private String dateFin;
    //un abonnement appartient a un seul membre OneToOne
    @OneToOne()
    @JoinColumn(name = "membre_id")
    private Membre membre;

    public Abonnement() {
    }

    public Abonnement(Long id, String type, String dateDebut, String dateFin, Membre membre) {
        this.id = id;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.membre = membre;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }
}
