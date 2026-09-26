package com.example.CRUD1.controller;

import com.example.CRUD1.dto.LivreRequestDTO;
import com.example.CRUD1.dto.LivreResponseDTO;
import com.example.CRUD1.entity.Livre;
import com.example.CRUD1.mapper.LivreMapper;
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
    public List<LivreResponseDTO> lister() {
        return LivreMapper.toResponseDTOList(livreService.getAll());
    }
    //recuperer un livre
    @GetMapping("/{id}")
    public LivreResponseDTO rechercher(@PathVariable Long id) {
        return LivreMapper.toResponseDTO(livreService.rechercher(id));
    }

    //inserer un livre
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivreResponseDTO inserer(@Valid @RequestBody LivreRequestDTO dto) {
        return LivreMapper.toResponseDTO(livreService.inserer(LivreMapper.toEntity(dto)));
    }

    //modifier un livre
    @PutMapping("/{id}")
    public LivreResponseDTO modifier(@Valid @RequestBody LivreRequestDTO dto, @PathVariable Long id) {
        return LivreMapper.toResponseDTO(livreService.modifier(LivreMapper.toEntity(dto), id));
    }

    //supprimer un livre
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        livreService.delete(id);
    }
}
