package com.example.CRUD1.controller;

import com.example.CRUD1.entity.Produit;
import com.example.CRUD1.service.ProduitService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/produits")
public class ProduitController {
    private final ProduitService produitService;


    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }
    @GetMapping
    public List<Produit> getAll(){return produitService.listeProduit();}

    @GetMapping("/{id}")
    public Produit getOne(@PathVariable Long id){
        return produitService.rechercherProduit(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produit create(@RequestBody Produit p){
        return produitService.ajouter(p);
    }

    @PutMapping("/{id}")
    public Produit update(@PathVariable Long id,@RequestBody Produit p){
        return produitService.modifier(id,p);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        produitService.supprimer(id);
    }

}
