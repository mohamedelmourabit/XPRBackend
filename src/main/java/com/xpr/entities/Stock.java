package com.xpr.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "stocks")
public class Stock implements Serializable  {
	
	@Id @GeneratedValue
	private long id;
	
	@ManyToOne
	private Client client;
		
	@ManyToOne
	private Variante variante;
	
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
	
	@OneToMany(mappedBy = "stock",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<LigneColis> lignesCommandes=new HashSet<LigneColis>();
	
	
	public Stock() {
		
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


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public int getQteLivre() {
		return qteLivre;
	}


	public void setQteLivre(int qteLivre) {
		this.qteLivre = qteLivre;
	}


	public int getQteEnCoursLivraison() {
		return qteEnCoursLivraison;
	}


	public void setQteEnCoursLivraison(int qteEnCoursLivraison) {
		this.qteEnCoursLivraison = qteEnCoursLivraison;
	}


	public int getQteNonLivre() {
		return qteNonLivre;
	}


	public void setQteNonLivre(int qteNonLivre) {
		this.qteNonLivre = qteNonLivre;
	}


	public Agence getAgence() {
		return agence;
	}


	public void setAgence(Agence agence) {
		this.agence = agence;
	}


	public Ville getVille() {
		return ville;
	}


	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public Variante getVariante() {
		return variante;
	}

	public void setVariante(Variante variante) {
		this.variante = variante;
	}

	public Set<LigneColis> getLignesCommandes() {
		return lignesCommandes;
	}

	public void setLignesCommandes(Set<LigneColis> lignesCommandes) {
		this.lignesCommandes = lignesCommandes;
	}


	

	
	

}
