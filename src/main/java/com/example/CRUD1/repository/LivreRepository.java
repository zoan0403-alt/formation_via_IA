package com.example.CRUD1.repository;

import com.example.CRUD1.entity.Auteur;
import com.example.CRUD1.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivreRepository extends JpaRepository<Livre,Long> {

    List<Livre> findByAuteur(Auteur auteur);
}
