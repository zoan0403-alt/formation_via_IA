package com.example.CRUD1.controller;

import com.example.CRUD1.dto.CategorieRequestDTO;
import com.example.CRUD1.dto.CategorieSansLivreDTO;
import com.example.CRUD1.entity.Categorie;
import com.example.CRUD1.mapper.CategorieMapper;
import com.example.CRUD1.service.CategorieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorie")
public class CategorieController {
    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    //ajouter une categorie
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CategorieSansLivreDTO insert(@Valid @RequestBody CategorieRequestDTO dto){
        return CategorieMapper.toReponseDTO(categorieService.insert(CategorieMapper.toEntity(dto)));
    }
    //modifier une categorie
    @PutMapping("/{id}")
    public CategorieSansLivreDTO update(@Valid @RequestBody CategorieRequestDTO categorie, @PathVariable Long id){
        return CategorieMapper.toReponseDTO(categorieService.update(CategorieMapper.toEntity(categorie),id));
    }
    //rechercher une categorie
    @GetMapping("/{id}")
    public CategorieSansLivreDTO getOne(@PathVariable Long id){
        return CategorieMapper.toReponseDTO(categorieService.getOne(id));
    }
    //rechercher toutes les categories
    @GetMapping()
    public List<CategorieSansLivreDTO> getAll(){
        return CategorieMapper.toList(categorieService.getAll());
    }
    //supprimer un livre
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        categorieService.delete(id);
    }
    //avoir tous les livres d'une categorie

}
