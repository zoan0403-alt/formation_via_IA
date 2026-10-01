package com.example.CRUD1.gestionDeSalleDeSport.service;

import com.example.CRUD1.gestionDeSalleDeSport.entity.Coach;
import com.example.CRUD1.gestionDeSalleDeSport.entity.Cours;
import com.example.CRUD1.gestionDeSalleDeSport.repository.CoachRepository;
import com.example.CRUD1.gestionDeSalleDeSport.repository.CoursRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CoachService {
    private final CoachRepository coachRepository;
    private final CoursRepository coursRepository;

    public CoachService(CoachRepository coachRepository, CoursRepository coursRepository) {
        this.coachRepository = coachRepository;
        this.coursRepository = coursRepository;
    }

    //ajouter un coach
    public Coach insert(Coach coach){
        return coachRepository.save(coach);
    }
    //rechercher un coach
    public Coach getOne(Long id){
        return coachRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Coach introuvable"));
    }
    //rechercher tous les coach
    public List<Coach> getAll(){
        return coachRepository.findAll();
    }
    //modifier un coach
    public Coach update(Long id, Coach coach){
        Coach existant= getOne(id);
        if(coach.getNom()!=null){existant.setNom(coach.getNom());}
        if(coach.getPrenom()!=null){existant.setPrenom(coach.getPrenom());}
        return coachRepository.save(existant);
    }
    //suprimer un coach
    public void delete(Long id){
        Coach existant=getOne(id);
        coachRepository.delete(existant);
    }
    //obtenir la liste des cours d'un coach
    public List<Cours> mesCours(Long id){
        Coach coach=getOne(id);
        return coursRepository.findByCoach(coach);
    }
}
