package com.xpr.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ligneColis")
public class LigneColis implements Serializable {
	
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	private Colis colis;
	
	@ManyToOne
	private Produit produit;
	
	@ManyToOne
	private Variante variante;
	
	@ManyToOne
	private Stock stock;
	
	private int qte;
	
	@ManyToOne
	private BonRetour bonRetour;
	
	
	public LigneColis() {
		
	}

	public Colis getColis() {
		return colis;
	}

	public void setColis(Colis colis) {
		this.colis = colis;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public Variante getVariante() {
		return variante;
	}

	public void setVariante(Variante variante) {
		this.variante = variante;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
