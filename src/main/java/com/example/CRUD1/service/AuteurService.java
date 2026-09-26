package com.example.CRUD1.service;

import com.example.CRUD1.dto.AuteurRequestDTO;
import com.example.CRUD1.entity.Auteur;
import com.example.CRUD1.repository.AuteurRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AuteurService {
    private final AuteurRepository auteurRepository;

    public AuteurService(AuteurRepository auteurRepository) {
        this.auteurRepository = auteurRepository;
    }
    //creer un auteur
    public Auteur insert(Auteur auteur){
        return auteurRepository.save(auteur);
    }
    //modifier un auteur
    public Auteur update(Auteur auteur,Long id){
        Auteur existant=getOne(id);
        existant.setNom(auteur.getNom());
        existant.setDateNaissance(auteur.getDateNaissance());
        existant.setNationalite(auteur.getNationalite());
        return auteurRepository.save(existant);
    }
    //rechercher un auteur
    public Auteur getOne(Long id){
        return  auteurRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Auteur introuvable"));
    }
    //lire la liste des auteurs
    public List<Auteur> getAll(){
        return auteurRepository.findAll();
    }
    //suprimer un auteur
    public void delete(Long id){
        auteurRepository.delete(getOne(id));
    }
}
