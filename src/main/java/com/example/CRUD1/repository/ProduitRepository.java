package com.example.CRUD1.repository;

import com.example.CRUD1.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
    // les requettes pas defaut y sont deja.

    List<Produit> findByPrixBetween(double min,double max);
}
