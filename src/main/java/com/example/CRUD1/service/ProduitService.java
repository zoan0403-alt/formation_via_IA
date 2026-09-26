package com.example.CRUD1.service;

import com.example.CRUD1.entity.Produit;
import com.example.CRUD1.repository.ProduitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProduitService {
    private final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }
    //la liste de tous les produits en bd
    public List<Produit> listeProduit(){
        return produitRepository.findAll();
    }
    //pour selectionner un produit
    public Produit rechercherProduit(Long id){

       return produitRepository.findById(id)
               .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Produit introuvable"));
    }

    // ajouter un produit
    public Produit ajouter(Produit produit){
        return produitRepository.save(produit);
    }

    //modifier un produit
    public Produit modifier(Long id,Produit produit){
        Produit existant= rechercherProduit(id);
        existant.setNom(produit.getNom());
        existant.setPrix(produit.getPrix());
        return produitRepository.save(existant);
    }

    //suprimer un produit
    public void supprimer(Long id){
        Produit existant=rechercherProduit(id);
        produitRepository.delete(existant);
    }

    //rechercher dans une fouchette de prix
    public List<Produit> fouchette(double min, double max){
        if(min > max){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"fouchette de prix invalide: le min doit etre inferieur au max");
        }
        return produitRepository.findByPrixBetween(min,max);
    }

}
