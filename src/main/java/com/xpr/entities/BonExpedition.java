package com.xpr.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name="bonExpeditions")
public class BonExpedition implements Serializable  {
	
	@Id 
	@GenericGenerator(name = "bonExpedition_nom", strategy = "com.xpr.generator.BonExpeditionGenerator")
    @GeneratedValue(generator = "bonExpedition_nom") 
	private String nom;
		
	private String statut;
	

	private String logistique;
	
	private String refBonLogistique;
	
	private Date dateCreation;
	
	private Date dateModification;
	
	@ManyToOne
	private Agence depart;
	
	@ManyToOne
	private Agence destination;
	
	@OneToMany(mappedBy = "bonExpedition")
	private Set<Colis> colis=new HashSet<>();
	
	@OneToMany(mappedBy = "bonExpedition",fetch = FetchType.EAGER)
	private Set<Historique> historiques=new HashSet<Historique>();
	
	@ManyToOne
	private Utilisateur creerPar;
	
	private boolean disabled;
	
	@ManyToOne
	private Client client;
	
	public BonExpedition() {
	}



	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}


	

	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getLogistique() {
		return logistique;
	}

	public void setLogistique(String logistique) {
		this.logistique = logistique;
	}

	public String getRefBonLogistique() {
		return refBonLogistique;
	}

	public void setRefBonLogistique(String refBonLogistique) {
		this.refBonLogistique = refBonLogistique;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Agence getDepart() {
		return depart;
	}

	public void setDepart(Agence depart) {
		this.depart = depart;
	}

	public Agence getDestination() {
		return destination;
	}

	public void setDestination(Agence destination) {
		this.destination = destination;
	}

	public Set<Colis> getColis() {
		return colis;
	}

	public void setColis(Set<Colis> colis) {
		this.colis = colis;
	}

	public Set<Historique> getHistoriques() {
		return historiques;
	}

	public void setHistoriques(Set<Historique> historiques) {
		this.historiques = historiques;
	}

	public Utilisateur getCreerPar() {
		return creerPar;
	}

	public void setCreerPar(Utilisateur creerPar) {
		this.creerPar = creerPar;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}



	public Client getClient() {
		return client;
	}



	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
	
	

}
