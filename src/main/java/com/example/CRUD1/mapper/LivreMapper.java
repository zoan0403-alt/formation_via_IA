package com.example.CRUD1.mapper;

import com.example.CRUD1.dto.AuteurResponseDTO;
import com.example.CRUD1.dto.LivreRequestDTO;
import com.example.CRUD1.dto.LivreResponseDTO;
import com.example.CRUD1.dto.LivreSansAuteurDTO;
import com.example.CRUD1.entity.Livre;

import java.util.ArrayList;
import java.util.List;

public class LivreMapper {

    public static Livre toEntity(LivreRequestDTO dto){
        return new Livre(null, dto.getTitre(),  dto.getAnneePublication(), dto.getDisponibiliter(),null);
    }
    public static LivreResponseDTO toResponseDTO(Livre livre){
        AuteurResponseDTO auteur=new AuteurResponseDTO(livre.getAuteur().getId(),livre.getAuteur().getNom(),livre.getAuteur().getNationalite(),livre.getAuteur().getDateNaissance());
        return new LivreResponseDTO(livre.getId(), livre.getTitre(),auteur, livre.getAnneePublication(), livre.getDisponibiliter(),CategorieMapper.toList(livre.getCategories()));
    }
    public static LivreSansAuteurDTO toLivreSansAuteurDTO(Livre livre){
        return new LivreSansAuteurDTO(livre.getId(), livre.getTitre(), livre.getAnneePublication(),livre.getDisponibiliter());
    }
    public static List<LivreResponseDTO> toResponseDTOList(List<Livre> livres){
        List<LivreResponseDTO> liste=new ArrayList<>();
        for(Livre livre:livres){
            liste.add(LivreMapper.toResponseDTO(livre));
        }
        return liste;
    }
}
