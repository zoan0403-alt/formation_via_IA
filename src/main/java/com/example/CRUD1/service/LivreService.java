package com.example.CRUD1.service;

import com.example.CRUD1.entity.Livre;
import com.example.CRUD1.repository.LivreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class LivreService {
    private final LivreRepository livreRepository;

    //Constructeur
    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }
    //liste de tous les livre
    public List<Livre> getAll(){
        return livreRepository.findAll();
    }
    //rechercher un livre
    public  Livre rechercher(Long id){
        return livreRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Livre introuvable"));
    }

    //inserer un livre
    public Livre inserer(Livre livre){
        if(livre.getDisponibiliter()==null){
            livre.setDisponibiliter(true);
        }
        return livreRepository.save(livre);
    }
    //modifier un livre
    public Livre modifier(Livre livre,Long id){
        Livre existant = rechercher(id);
        existant.setAuteur(livre.getAuteur());
        existant.setTitre(livre.getTitre());
        if(livre.getDisponibiliter()!=null){
            existant.setDisponibiliter(livre.getDisponibiliter());
        }
        existant.setAnneePublication(livre.getAnneePublication());
        return livreRepository.save(existant);
    }

    //suprimer un livre
    public  void delete(Long id){
        Livre existant=rechercher(id);
        livreRepository.delete(existant);
    }

}
