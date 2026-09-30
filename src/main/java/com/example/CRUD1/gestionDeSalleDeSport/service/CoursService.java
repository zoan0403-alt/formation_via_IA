package com.example.CRUD1.gestionDeSalleDeSport.service;

import com.example.CRUD1.gestionDeSalleDeSport.dto.CoachSimpleReponseDTO;
import com.example.CRUD1.gestionDeSalleDeSport.entity.Coach;
import com.example.CRUD1.gestionDeSalleDeSport.entity.Cours;
import com.example.CRUD1.gestionDeSalleDeSport.entity.Membre;
import com.example.CRUD1.gestionDeSalleDeSport.repository.CoursRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CoursService {
    private final CoursRepository coursRepository;

    public CoursService(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }
    //ajouter un cours
    public Cours insert(Cours cours){
       return coursRepository.save(cours);
    }
    //rechercher un cours 
    public Cours getOne(Long id){
        return coursRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cours introuvable !"));
    }
    //rechercher tous les cours
    public List<Cours> getAll(){
        return coursRepository.findAll();
    }
    //modifier un cours
    public Cours update(Cours cours, Long id){
        Cours existant=getOne(id);
        if(cours.getNom()!=null){existant.setNom(cours.getNom());}
        if(cours.getDuree()>0){existant.setDuree(cours.getDuree());}
        if(cours.getCoach()!=null){existant.setCoach(cours.getCoach());}
        //on vas ecrire les methodes a part pour suprimer a jouter des membres
        return coursRepository.save(existant);        
    }
    //suprimer un cours 
    public void delete(Long id){
        Cours existant=getOne(id);
        coursRepository.save(existant);
    }
    //ajouter un participant
    public Cours addMembre(Long id, Membre membre){
        Cours cours=getOne(id);
        cours.getParticipants().add(membre);
        return coursRepository.save(cours);
    }
    //rechercher tous les membres d'un cours
    public List<Membre> getMembres(Long id){
        Cours cours=getOne(id);
        return cours.getParticipants();
    }
    //suprimer un participant
    public void deleteMembre(Long id, Membre membre ){ Cours cours=getOne(id);
        List<Membre> membres = getMembres(id);
        for(Membre membre1 : membres){
            if(membre1==membre){
                membres.remove(membre1);
            }
        }
        coursRepository.save(cours);
    }
    //supprimer tous les cours (je sais pas si c'est important mais pour ca il faut suprimer en cascade)
    public void deleteAll(){
        coursRepository.deleteAll();
    }
    //rechercher le coach d'un cours
    public Coach getCoach( Long id){
        Cours cours=getOne(id);
        return cours.getCoach();
    }
}
