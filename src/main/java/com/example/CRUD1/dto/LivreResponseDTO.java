package com.example.CRUD1.dto;

public class LivreResponseDTO {
    private Long id;
    private String titre;
    private String auteur;
    private int anneePublication;
    private Boolean disponibiliter;

    public LivreResponseDTO() {}

    public LivreResponseDTO(Long id, String titre, String auteur, int anneePublication, Boolean disponibiliter) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.disponibiliter = disponibiliter;
    }

    public Long getId() {
        return id;
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
