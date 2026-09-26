package com.example.CRUD1.controller;

import com.example.CRUD1.dto.ProduitRequestDTO;
import com.example.CRUD1.dto.ProduitResponseDTO;
import com.example.CRUD1.entity.Produit;
import com.example.CRUD1.mapper.ProduitMapper;
import com.example.CRUD1.service.ProduitService;
import jakarta.validation.Valid;
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
    public List<ProduitResponseDTO> getAll(){
        return ProduitMapper.toResponseDTOList(produitService.listeProduit());
    }

    @GetMapping("/{id}")
    public ProduitResponseDTO getOne(@PathVariable Long id){
        return ProduitMapper.toResponseDTO(produitService.rechercherProduit(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProduitResponseDTO create(@Valid @RequestBody ProduitRequestDTO dto){
        Produit produit=ProduitMapper.toEntity(dto);
        produit=produitService.ajouter(produit);
         return ProduitMapper.toResponseDTO(produit);
    }

    @PutMapping("/{id}")
    public ProduitResponseDTO update(@PathVariable Long id,@Valid @RequestBody ProduitRequestDTO p){
       return ProduitMapper.toResponseDTO(produitService.modifier(id,ProduitMapper.toEntity(p)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        produitService.supprimer(id);
    }
    @GetMapping("/recherche")
    public List<ProduitResponseDTO> fouchette(@RequestParam double prixMin, @RequestParam double prixMax){
        return ProduitMapper.toResponseDTOList(produitService.fouchette(prixMin,prixMax));
    }
}
