package com.xpr.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "historiqueStock")
public class HistoriqueStock implements Serializable{
	
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	private Stock stock;
	
	private String action;
	
	@ManyToOne
	private Variante variante;
	
	private int qte;
	
	private Date creationDate;
	
	@ManyToOne
	private Utilisateur utilisateur;
	
	
	public HistoriqueStock() {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	public int getQte() {
		return qte;
	}


	public void setQte(int qte) {
		this.qte = qte;
	}


	public Stock getStock() {
		return stock;
	}


	public void setStock(Stock stock) {
		this.stock = stock;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public Variante getVariante() {
		return variante;
	}


	public void setVariante(Variante variante) {
		this.variante = variante;
	}

	

}
