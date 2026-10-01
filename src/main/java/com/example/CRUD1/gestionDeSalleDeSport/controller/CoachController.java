package com.example.CRUD1.gestionDeSalleDeSport.controller;

import com.example.CRUD1.gestionDeSalleDeSport.dto.CoachRequestDTO;
import com.example.CRUD1.gestionDeSalleDeSport.dto.CoachSimpleReponseDTO;
import com.example.CRUD1.gestionDeSalleDeSport.entity.Coach;
import com.example.CRUD1.gestionDeSalleDeSport.entity.Cours;
import com.example.CRUD1.gestionDeSalleDeSport.mapper.CoachMapper;
import com.example.CRUD1.gestionDeSalleDeSport.service.CoachService;
import com.example.CRUD1.gestionDeSalleDeSport.service.CoursService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/coach")
public class CoachController {
    private final CoachService coachService;
    private final CoursService coursService;

    public CoachController(CoachService coachService, CoursService coursService) {
        this.coachService = coachService;
        this.coursService = coursService;
    }
    //ajouter un coach
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CoachSimpleReponseDTO insert(@Valid CoachRequestDTO dto){
        return CoachMapper.toSimpleDto(coachService.insert(CoachMapper.toEntity(dto)));
    }
    //rechercher un coach
    @GetMapping("/{id}")
    public CoachSimpleReponseDTO getOne(@PathVariable Long id){
        return CoachMapper.toSimpleDto(coachService.getOne(id));
    }
    //rechercher tous les coach
    @GetMapping()
    public List<CoachSimpleReponseDTO> getAll(){
        return CoachMapper.toListDTO(coachService.getAll());
    }
    //modifier un coach
    @PutMapping("/{id}")
    public CoachSimpleReponseDTO update(@PathVariable Long id, @Valid CoachRequestDTO dto){
        return CoachMapper.toSimpleDto(coachService.update(id,CoachMapper.toEntity(dto)));
    }
    //supprimer un coach
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        // on verifie d'abord qu'il pas de cours a son actif
        List<Cours> cours=coachService.mesCours(id);
        if (cours.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Impossible de supprimer ce prof car il dispose des cours a son actif");
        }
        coachService.delete(id);
    }
}
