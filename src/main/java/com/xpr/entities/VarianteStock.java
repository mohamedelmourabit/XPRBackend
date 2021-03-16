package com.xpr.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "varianteStocks")
public class VarianteStock implements Serializable  {
	
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	private Variante variante;
	
	@ManyToOne
	private Stock stock;
	
	private int qte;
	
	
	public VarianteStock() {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Variante getVariante() {
		return variante;
	}


	public void setVariante(Variante variante) {
		this.variante = variante;
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

	
	
	
}
