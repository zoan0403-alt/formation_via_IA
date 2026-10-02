package com.example.CRUD1.gestionDeSalleDeSport.mapper;

import com.example.CRUD1.gestionDeSalleDeSport.dto.CoursRequestDTO;
import com.example.CRUD1.gestionDeSalleDeSport.dto.CoursResponseDTO;
import com.example.CRUD1.gestionDeSalleDeSport.entity.Cours;

import java.util.ArrayList;
import java.util.List;

public class CoursMapper {

    public static Cours toEntity(CoursRequestDTO dto){
        return new Cours(null, dto.getNom(), dto.getDuree(), null,null);
    }
    public static CoursResponseDTO toSimpleDTO(Cours cours){
        return new CoursResponseDTO(cours.getId(), cours.getNom(), cours.getDuree(), CoachMapper.toSimpleDto(cours.getCoach()));
    }
    public static List<CoursResponseDTO> toList(List<Cours> cours){
        List<CoursResponseDTO> liste= new ArrayList<>();
        for (Cours c: cours){
            liste.add(toSimpleDTO(c));
        }
        return liste;
    }
    
}
