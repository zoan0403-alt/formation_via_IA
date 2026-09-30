package com.example.CRUD1.gestionDeSalleDeSport.repository;

import com.example.CRUD1.gestionDeSalleDeSport.entity.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursRepository extends JpaRepository<Cours,Long> {

}
