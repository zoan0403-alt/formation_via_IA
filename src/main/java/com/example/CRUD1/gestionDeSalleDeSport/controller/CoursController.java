package com.example.CRUD1.gestionDeSalleDeSport.controller;

import com.example.CRUD1.gestionDeSalleDeSport.dto.CoursRequestDTO;
import com.example.CRUD1.gestionDeSalleDeSport.dto.CoursResponseDTO;
import com.example.CRUD1.gestionDeSalleDeSport.entity.Coach;
import com.example.CRUD1.gestionDeSalleDeSport.entity.Cours;
import com.example.CRUD1.gestionDeSalleDeSport.mapper.CoursMapper;
import com.example.CRUD1.gestionDeSalleDeSport.service.CoachService;
import com.example.CRUD1.gestionDeSalleDeSport.service.CoursService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cours")
public class CoursController {
    private final CoursService coursService;
    private final CoachService coachService;

    public CoursController(CoursService coursService, CoachService coachService) {
        this.coursService = coursService;
        this.coachService = coachService;
    }
    //ajouter un cours
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CoursResponseDTO insert(@Valid @RequestBody CoursRequestDTO dto){
        Coach coach=coachService.getOne(dto.getCoachId());
        Cours cours=CoursMapper.toEntity(dto);
        cours.setCoach(coach);
        return CoursMapper.toSimpleDTO(coursService.insert(cours));
    }
    //obtenir un cours
    @GetMapping("/{id}")
    public CoursResponseDTO getOne(@PathVariable Long id){
        return CoursMapper.toSimpleDTO(coursService.getOne(id));
    }
    //obtenir tous les cours
    @GetMapping()
    public List<CoursResponseDTO> getAll(){
        return CoursMapper.toList(coursService.getAll());
    }
    //modification d'un cours
    @PutMapping("/{id}")
    public CoursResponseDTO update(@PathVariable Long id,@Valid @RequestBody CoursRequestDTO dto){
        return CoursMapper.toSimpleDTO(coursService.update(CoursMapper.toEntity(dto),id));
    }
    //supprimer un cours
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        coursService.delete(id);
    }
}
