package com.example.CRUD1.repository;

import com.example.CRUD1.entity.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuteurRepository extends JpaRepository <Auteur,Long> {

}
