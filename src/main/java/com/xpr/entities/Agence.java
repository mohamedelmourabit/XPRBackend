package com.xpr.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "agences")
public class Agence implements Serializable  {
	
	@Id @GeneratedValue
	private long id;
	
	private String nom;
	
	private Ville ville;
	
	@OneToMany(mappedBy="agence",fetch=FetchType.LAZY )
	@JsonIgnore
	private Set<Stock> stocks=new HashSet<Stock>();
	
	
	public Agence() {
		
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public Ville getVille() {
		return ville;
	}


	public void setVille(Ville ville) {
		this.ville = ville;
	}


	public Set<Stock> getStocks() {
		return stocks;
	}


	public void setStocks(Set<Stock> stocks) {
		this.stocks = stocks;
	}
	
	

}
