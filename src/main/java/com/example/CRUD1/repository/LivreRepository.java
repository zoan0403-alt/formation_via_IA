package com.example.CRUD1.repository;

import com.example.CRUD1.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreRepository extends JpaRepository<Livre,Long> {
}
