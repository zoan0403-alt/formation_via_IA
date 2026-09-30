package com.example.CRUD1.dto;

import java.util.List;

public class LivreResponseDTO {
    private Long id;
    private String titre;
    private AuteurResponseDTO auteur;
    private int anneePublication;
    private Boolean disponibiliter;



    private List<CategorieSansLivreDTO> categories;

    public LivreResponseDTO() {}
    public LivreResponseDTO(Long id, String titre, AuteurResponseDTO auteur, int anneePublication, Boolean disponibiliter, List<CategorieSansLivreDTO> categories) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.disponibiliter = disponibiliter;
        this.categories = categories;
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

    public AuteurResponseDTO getAuteur() {
        return auteur;
    }

    public void setAuteur(AuteurResponseDTO auteur) {
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

    public List<CategorieSansLivreDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategorieSansLivreDTO> categories) {
        this.categories = categories;
    }
}
