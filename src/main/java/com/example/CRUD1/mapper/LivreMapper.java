package com.example.CRUD1.mapper;

import com.example.CRUD1.dto.LivreRequestDTO;
import com.example.CRUD1.dto.LivreResponseDTO;
import com.example.CRUD1.entity.Livre;

import java.util.ArrayList;
import java.util.List;

public class LivreMapper {

    public static Livre toEntity(LivreRequestDTO dto){
        return new Livre(null, dto.getTitre(), dto.getAuteur(), dto.getAnneePublication(), dto.getDisponibiliter());
    }
    public static LivreResponseDTO toLivreResponseDTO(Livre livre){
        return new LivreResponseDTO(livre.getId(), livre.getTitre(), livre.getAuteur(), livre.getAnneePublication(), livre.getDisponibiliter());
    }
    public static List<LivreResponseDTO> livreResponseDTOList(List<Livre> livres){
        List<LivreResponseDTO> liste=new ArrayList<>();
        for(Livre livre:livres){
            liste.add(LivreMapper.toLivreResponseDTO(livre));
        }
        return liste;
    }
}
