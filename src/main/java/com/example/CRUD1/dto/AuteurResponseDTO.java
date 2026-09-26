package com.example.CRUD1.dto;

import java.util.Date;

public class AuteurResponseDTO {
    private Long id;
    private String nom;
    private String nationalite;
    private Date dateNaissance;

    public AuteurResponseDTO() {
    }

    public AuteurResponseDTO(Long id, String nom, String nationalite, Date dateNaissance) {
        this.id = id;
        this.nom = nom;
        this.nationalite = nationalite;
        this.dateNaissance = dateNaissance;
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
}
