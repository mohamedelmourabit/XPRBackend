package com.xpr.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.xpr.entities.Produit;


@RepositoryRestResource(collectionResourceRel = "produits", path = "produits")
public interface ProduitRepository extends JpaRepository<Produit, Long> {
	

	public Produit findByNom(String nomProduit);
	
	public Produit findByReference(String reference);
	
	

}
