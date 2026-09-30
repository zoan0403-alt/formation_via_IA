package com.example.CRUD1.dto;

import com.example.CRUD1.entity.Categorie;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

public class LivreRequestDTO {
    @NotBlank(message = "Le titre ne peut etre vide.")
    private String titre;
    @NotNull(message = "l'id de l'auteur est obligatoire")
    @Positive(message = "l'id de l'auteur dit etre positif")
    private Long auteurId;
    @Positive(message = "l'annees de piblication doit etre nom vide et possitif")
    private int anneePublication;
    //il peut ne pas fournie et par defaut on mets le livre comme disponible (true) voir service
    private Boolean disponibiliter;

    @Size(min = 1,message = "le livre doit avoir au moins une categorie")
    //on mets juste la liste des id de la categorie le service vas gerer
    private List<Long> categorieIds;

    public LivreRequestDTO() {}

    public LivreRequestDTO(String titre, Long auteur, int anneePublication, Boolean disponibiliter,List<Long> categorieIds) {
        this.titre = titre;
        this.auteurId = auteur;
        this.anneePublication = anneePublication;
        this.disponibiliter = disponibiliter;
        this.categorieIds=categorieIds;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Long getAuteurId() {
        return auteurId;
    }

    public void setAuteurId(Long auteurId) {
        this.auteurId = auteurId;
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

    public List<Long> getCategorieIds() {
        return categorieIds;
    }

    public void setCategorieIds(List<Long> categorieIds) {
        this.categorieIds = categorieIds;
    }
}
