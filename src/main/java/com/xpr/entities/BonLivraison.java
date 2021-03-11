package com.xpr.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "bonLivraisons")
public class BonLivraison implements Serializable {
	
	@Id 
	@GenericGenerator(name = "bonLivraison_nom", strategy = "com.xpr.generator.BonLivraisonGenerator")
    @GeneratedValue(generator = "bonLivraison_nom") 
	private String nom;
	
	private Date dateCreation;
	
	private Date dateModification;
	
	@ManyToOne
	private Ramasseur ramasseur;
	
	@ManyToOne
	private Client client;
	
	@OneToMany(mappedBy = "bonLivraison",fetch = FetchType.EAGER)
	private Set<Colis> colis = new HashSet<Colis>();
	
	@ManyToOne
	private Facture facture;
	
	@ManyToOne
	private UtilisateurXpr creerPar;
	
	private String statut;
	
	private boolean disabled;
	
	@OneToMany(mappedBy = "bonLivraison",fetch = FetchType.LAZY)
	private Set<Historique> historiques=new HashSet<Historique>();
	
	
	public BonLivraison() {
		this.dateCreation = new Date();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<Colis> getColis() {
		return colis;
	}

	public void setColis(Set<Colis> colis) {
		this.colis = colis;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public UtilisateurXpr getCreerPar() {
		return creerPar;
	}

	public void setCreerPar(UtilisateurXpr creerPar) {
		this.creerPar = creerPar;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Ramasseur getRamasseur() {
		return ramasseur;
	}

	public void setRamasseur(Ramasseur ramasseur) {
		this.ramasseur = ramasseur;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Set<Historique> getHistoriques() {
		return historiques;
	}

	public void setHistoriques(Set<Historique> historiques) {
		this.historiques = historiques;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	
	
	
	
	

}