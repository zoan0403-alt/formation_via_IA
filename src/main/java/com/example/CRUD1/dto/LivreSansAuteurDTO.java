package com.example.CRUD1.dto;

public class LivreSansAuteurDTO {
    private Long id;
    private String titre;
    private int anneePublication;
    private Boolean disponibiliter;

    public LivreSansAuteurDTO() {

    }

    public LivreSansAuteurDTO(Long id, String titre, int anneePublication, Boolean disponibiliter) {
        this.id = id;
        this.titre = titre;
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
