package com.example.CRUD1.service;

import com.example.CRUD1.dto.LivreRequestDTO;
import com.example.CRUD1.dto.LivreResponseDTO;
import com.example.CRUD1.entity.Auteur;
import com.example.CRUD1.entity.Livre;
import com.example.CRUD1.mapper.LivreMapper;
import com.example.CRUD1.repository.AuteurRepository;
import com.example.CRUD1.repository.LivreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService {
    private final LivreRepository livreRepository;
    private final AuteurRepository auteurRepository;

    //Constructeur
    public LivreService(LivreRepository livreRepository, AuteurRepository auteurRepository) {
        this.livreRepository = livreRepository;
        this.auteurRepository = auteurRepository;
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
    public Livre inserer(LivreRequestDTO dto){

         Auteur auteur =auteurRepository.findById(dto.getAuteurId())
                 .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Auteur introuvable"));
        if(dto.getDisponibiliter()==null){
            dto.setDisponibiliter(true);
        }
        Livre livre = LivreMapper.toEntity(dto);
        livre.setAuteur(auteur);
        return livreRepository.save(livre);
    }
    //modifier un livre
    public Livre modifier(LivreRequestDTO dto, Long id){
        Auteur auteur =auteurRepository.findById(dto.getAuteurId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Auteur introuvable"));
        Livre existant = rechercher(id);
        existant.setAuteur(auteur);
        existant.setTitre(dto.getTitre());
        existant.setAnneePublication(dto.getAnneePublication());
        if(dto.getDisponibiliter()!=null){
            existant.setDisponibiliter(dto.getDisponibiliter());
        }
        return livreRepository.save(existant);
    }

    //suprimer un livre
    public  void delete(Long id){
        Livre existant=rechercher(id);
        livreRepository.delete(existant);
    }

}
