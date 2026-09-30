package com.example.CRUD1.service;

import com.example.CRUD1.dto.LivreResponseDTO;
import com.example.CRUD1.entity.Categorie;
import com.example.CRUD1.entity.Livre;
import com.example.CRUD1.repository.CategorieRepository;
import com.example.CRUD1.repository.LivreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategorieService {
    private final CategorieRepository categorieRepository;
    private final LivreRepository livreRepository;

    public CategorieService(CategorieRepository categorieRepository, LivreService livreService, LivreRepository livreRepository) {
        this.categorieRepository = categorieRepository;
        this.livreRepository = livreRepository;
    }
    //ajouter un livre
    public Categorie insert(Categorie categorie){
        return categorieRepository.save(categorie);
    }
    //rechercher une categorie
    public Categorie getOne(Long id){
        return categorieRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cette categorie n'existe pas."));
    }
    //modifier une categorie
    public Categorie update(Categorie categorie,Long id){
        Categorie existante=getOne(id);
                existante.setNom(categorie.getNom());
                existante.setDescription(categorie.getDescription());
                return categorieRepository.save(existante);
    }
    //suprimer une categorie
    public void delete(Long id){
        Categorie existante=getOne(id);
        categorieRepository.delete(existante);
    }
    //liste des categories
    public List<Categorie> getAll(){
        return categorieRepository.findAll();
    }
    //avoir tous les livres d'une categorie
//    public LivreResponseDTO mesLivres(Long id){
//        //livreRepository.findByCategorie
//    }

}
