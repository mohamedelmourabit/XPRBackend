package com.xpr.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "variantes")
public class Variante implements Serializable  {
	
	@Id @GeneratedValue
	private Long id;
	
	private String sku;
	
	private int qte;
	
	private double prix;
	
	private double prixAchat;
	
	private int qteReserve;
	

	@ManyToOne
	@JsonIgnore
	private Produit produit;
	
	public Variante() {
		
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public double getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(double prixAchat) {
		this.prixAchat = prixAchat;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQteReserve() {
		return qteReserve;
	}

	public void setQteReserve(int qteReserve) {
		this.qteReserve = qteReserve;
	}

	
	
}
