package com.example.CRUD1.mapper;

import com.example.CRUD1.dto.AuteurRequestDTO;
import com.example.CRUD1.dto.AuteurResponseDTO;
import com.example.CRUD1.entity.Auteur;

import java.util.ArrayList;
import java.util.List;

public class AuteurMapper {

    public static Auteur toEntity(AuteurRequestDTO dto){
        return new Auteur(null,dto.getNom(), dto.getNationalite(), dto.getDateNaissance());
    }
    public static AuteurResponseDTO toResponseDTO(Auteur auteur){
        return new AuteurResponseDTO(auteur.getId(), auteur.getNom(), auteur.getNationalite(), auteur.getDateNaissance());
    }
    public static List<AuteurResponseDTO> toResponseDTOList(List<Auteur> auteurs){
        List<AuteurResponseDTO> liste=new ArrayList<>();
        for(Auteur auteur: auteurs){
            liste.add(toResponseDTO(auteur));
        }
        return liste;
    }
}
