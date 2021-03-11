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
@Table(name = "Services")
public class Service implements Serializable  {
	
	@Id @GeneratedValue
	private long id;
	
	private String nom;
	
	@OneToMany(mappedBy = "service",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<UtilisateurXpr> utilisateurs = new HashSet<UtilisateurXpr>();
	
	public Service() {
		
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

	public Set<UtilisateurXpr> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(Set<UtilisateurXpr> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
	

}
