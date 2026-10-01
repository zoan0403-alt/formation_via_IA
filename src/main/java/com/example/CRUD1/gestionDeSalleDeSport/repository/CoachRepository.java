package com.example.CRUD1.gestionDeSalleDeSport.repository;

import com.example.CRUD1.gestionDeSalleDeSport.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach,Long> {

}
