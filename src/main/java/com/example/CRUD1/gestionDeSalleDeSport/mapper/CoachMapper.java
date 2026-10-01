package com.example.CRUD1.gestionDeSalleDeSport.mapper;


import com.example.CRUD1.gestionDeSalleDeSport.dto.CoachRequestDTO;
import com.example.CRUD1.gestionDeSalleDeSport.dto.CoachSimpleReponseDTO;
import com.example.CRUD1.gestionDeSalleDeSport.entity.Coach;

import java.util.ArrayList;
import java.util.List;

public class CoachMapper {

    public static Coach toEntity(CoachRequestDTO dto){
        return new Coach(null,dto.getNom(),dto.getPrenom(),null);
    }
    public static CoachSimpleReponseDTO toSimpleDto(Coach coach){
        return new CoachSimpleReponseDTO(coach.getId(), coach.getNom(), coach.getPrenom());
    }
    public static List<CoachSimpleReponseDTO> toListDTO(List<Coach> coaches){
        List<CoachSimpleReponseDTO> liste=new ArrayList<>();
        for (Coach coach:coaches){
            liste.add(toSimpleDto(coach));
        }
        return liste;
    }
}
