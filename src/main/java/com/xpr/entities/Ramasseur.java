package com.xpr.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Ramasseurs")
@DiscriminatorValue("Ramasseur")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "typeUtilisateur", discriminatorType = DiscriminatorType.STRING)
public class Ramasseur extends Utilisateur implements Serializable {
		
	private String nom;
	
	private String prenom;
	
	private String telephone;

	
	@OneToMany(mappedBy="ramasseur",fetch=FetchType.LAZY )
	@JsonIgnore
	private Set<Colis> colis = new HashSet<Colis>();
	
	@OneToMany(mappedBy="ramasseur",fetch=FetchType.LAZY )
	@JsonIgnore
	private Set<BonRamassage> bonRamassages=new HashSet<BonRamassage>();
	
	
	@OneToMany(mappedBy="ramasseur",fetch=FetchType.LAZY )
	@JsonIgnore
	private Set<BonLivraison> bonLivraisons = new HashSet<BonLivraison>();
	
	
	public Ramasseur() {
		
		this.typeUtilisateur="Ramasseur";
		
		
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

	
	public Set<Colis> getColis() {
		return colis;
	}

	public void setColis(Set<Colis> colis) {
		this.colis = colis;
	}
	
	

}
