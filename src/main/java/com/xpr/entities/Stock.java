package com.xpr.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stocks")
public class Stock implements Serializable  {
	
	@Id @GeneratedValue
	private long id;
	
	@ManyToOne
	private Client client;
	
	@ManyToOne
	private Produit produit;
	
	@ManyToOne
	private Livreur livreur;
	
	private int qte;
	
	private int qteLivre;
	
	private int qteEnCoursLivraison;
	
	private int qteNonLivre;
	
	@ManyToOne
	private Agence agence;
	
	@ManyToOne
	private Ville ville;
	
	
	public Stock() {
		
	}


	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	
	public Livreur getLivreur() {
		return livreur;
	}

	public void setLivreur(Livreur livreur) {
		this.livreur = livreur;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}


	

}
