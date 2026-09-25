package com.example.CRUD1.controller;

import com.example.CRUD1.entity.Livre;
import com.example.CRUD1.service.LivreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
public class LivreController {
    private final LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    //liste des livres
    @GetMapping
    public List<Livre> lister() {
        return livreService.getAll();
    }

    //recuperer un livre
    @GetMapping("/{id}")
    public Livre rechercher(@PathVariable Long id) {
        return livreService.rechercher(id);
    }

    //inserer un livre
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Livre inserer(@Valid @RequestBody Livre livre) {
        return livreService.inserer(livre);
    }

    //modifier un livre
    @PutMapping("/{id}")
    public Livre modifier(@RequestBody Livre livre, @PathVariable Long id) {
        return livreService.modifier(livre, id);
    }

    //supprimer un livre
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        livreService.delete(id);
    }
}
