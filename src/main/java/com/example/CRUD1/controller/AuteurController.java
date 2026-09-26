package com.example.CRUD1.controller;

import com.example.CRUD1.dto.AuteurRequestDTO;
import com.example.CRUD1.dto.AuteurResponseDTO;
import com.example.CRUD1.entity.Auteur;
import com.example.CRUD1.mapper.AuteurMapper;
import com.example.CRUD1.service.AuteurService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auteurs")
public class AuteurController {
    private final AuteurService auteurService;

    public AuteurController(AuteurService auteurService) {
        this.auteurService = auteurService;
    }
    //aujouter un auteur
    @PostMapping()
    public AuteurResponseDTO insert(@Valid @RequestBody AuteurRequestDTO dto){
        return AuteurMapper.toRespondeDTO(auteurService.insert(AuteurMapper.toEntity(dto)));
    }
    //modifier un auteur
    @PutMapping("/{id}")
    public AuteurResponseDTO update(@Valid @RequestBody AuteurRequestDTO dto,@PathVariable Long id){
        return AuteurMapper.toRespondeDTO(auteurService.update(AuteurMapper.toEntity(dto),id));
    }
    //rechercher un auteur
    @GetMapping("/{id}")
    public AuteurResponseDTO getOne(@PathVariable Long id){return AuteurMapper.toRespondeDTO(auteurService.getOne(id));}
    //liste de tous les auteurs
    @GetMapping()
    public List<AuteurResponseDTO> getAll(){return AuteurMapper.toResponseDTOList(auteurService.getAll());}
    //suppprimer un auteur
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ auteurService.delete(id);}

}
