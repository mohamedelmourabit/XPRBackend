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

@Entity
@Table(name = "business")
public class Business implements Serializable  {
	
	@Id @GeneratedValue
	private Long id;
	
	private String nom;
	
	@ManyToOne
	private Client client;
	
	@OneToMany(mappedBy = "business",fetch = FetchType.LAZY)
	private Set<Colis> colis=new HashSet<Colis>();

	
	
	public Business() {
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}
	
	

}
