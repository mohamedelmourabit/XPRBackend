package com.xpr.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "livreurs")
@DiscriminatorValue("Livreur")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "typeUtilisateur", discriminatorType = DiscriminatorType.STRING)
public class Livreur extends Ramasseur implements Serializable {
		
	private String nom;
	
	private String prenom;
	
	private String telephone;
	
	@ManyToMany(fetch=FetchType.LAZY )
	private Set<Ville> villes = new HashSet<Ville>();
	
	@OneToMany(mappedBy="livreur",fetch=FetchType.LAZY )
	@JsonIgnore
	private Set<Colis> colis = new HashSet<Colis>();

	
	@OneToMany(mappedBy="livreur",fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<Stock> stocks=new HashSet<Stock>();
	
	@OneToMany(mappedBy="livreur",fetch=FetchType.LAZY )
	@JsonIgnore
	private Set<Facture> factures = new HashSet<Facture>();
	
	

	
	public Livreur() {
		
		this.typeUtilisateur="Livreur";
		
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public Set<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(Set<Stock> stocks) {
		this.stocks = stocks;
	}

	public Set<Facture> getFactures() {
		return factures;
	}

	public void setFactures(Set<Facture> factures) {
		this.factures = factures;
	}

	public Set<Ville> getVilles() {
		return villes;
	}

	public void setVilles(Set<Ville> villes) {
		this.villes = villes;
	}

	public Set<Colis> getColis() {
		return colis;
	}

	public void setColis(Set<Colis> colis) {
		this.colis = colis;
	}
	
	

}
