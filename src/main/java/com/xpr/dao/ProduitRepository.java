package com.xpr.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.xpr.entities.Produit;



public interface ProduitRepository extends JpaRepository<Produit, Long> {
	

	public Produit findByNom(String nomProduit);
	
	public Produit findByReference(String reference);
	
	

}
