package com.example.CRUD1.mapper;

import com.example.CRUD1.dto.CategorieRequestDTO;
import com.example.CRUD1.dto.CategorieSansLivreDTO;
import com.example.CRUD1.entity.Categorie;

import java.util.ArrayList;
import java.util.List;

public class CategorieMapper {
    public static Categorie toEntity(CategorieRequestDTO dto){
        return new Categorie(null,dto.getNom(),dto.getDescription());
    }
    public static CategorieSansLivreDTO toReponseDTO(Categorie categorie){
        return  new CategorieSansLivreDTO(categorie.getId(), categorie.getNom(), categorie.getDescription());
    }
    public static List<CategorieSansLivreDTO> toList(List<Categorie> categories){
        List<CategorieSansLivreDTO> liste= new ArrayList<>();
        for(Categorie categorie:categories){
            liste.add(toReponseDTO(categorie));
        }
        return liste;
    }
}
