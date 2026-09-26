package com.example.CRUD1.mapper;

import com.example.CRUD1.dto.ProduitRequestDTO;
import com.example.CRUD1.dto.ProduitResponseDTO;
import com.example.CRUD1.entity.Produit;

import java.util.ArrayList;
import java.util.List;

public class ProduitMapper {
    //convertir un Produitdto d'entree en produit utilisable dans le service
    public static Produit toEntity(ProduitRequestDTO dto){
        Produit p =new Produit(null,dto.getNom(), dto.getPrix());
        return p;
    }
    //convertir un produit en responseProduitDto
    public static ProduitResponseDTO toResponseDTO(Produit p){
        ProduitResponseDTO dto=new ProduitResponseDTO(p.getId(),p.getNom(),p.getPrix());
        return dto;
    }
    //convertir une liste de produits en une liste de produitResponseDto
    public static List<ProduitResponseDTO> toResponseDTOList(List<Produit> produits){
        List<ProduitResponseDTO> liste=new ArrayList<>();
        for(Produit p:produits){
           liste.add(ProduitMapper.toResponseDTO(p));
        }
        return liste;
    }
}
