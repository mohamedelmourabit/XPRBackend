package com.xpr.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Utilisateur")
@DiscriminatorValue("Utilisateur")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "typeUtilisateur", discriminatorType = DiscriminatorType.STRING)
public class UtilisateurXpr extends Utilisateur {
	
	private String nom;
	
	private String prenom;
	
	private String telephone;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Business> Business=new HashSet<Business>();
	
	@ManyToOne
	@JsonIgnore
	private Client client;
	
	
	public UtilisateurXpr() {
		this.typeUtilisateur="Utilisateur";
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


	public Set<Business> getBusiness() {
		return Business;
	}


	public void setBusiness(Set<Business> business) {
		Business = business;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}
	
	

}
