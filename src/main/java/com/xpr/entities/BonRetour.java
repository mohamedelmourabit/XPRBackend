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
@Table(name = "bonRetour")
public class BonRetour implements Serializable {
	
	@Id 
	@GenericGenerator(name = "bonRetour_nom", strategy = "com.xpr.generator.BonRetourGenerator")
    @GeneratedValue(generator = "bonRetour_nom") 
	private String nom;
	
	
	private Date dateCreation;
	
	private Date dateModification;
	
	@ManyToOne
	private Livreur livreur;
	
	@ManyToOne
	private Client client;
	
	@OneToMany(mappedBy = "bonRetour",fetch = FetchType.EAGER)
	private Set<Colis> colis=new HashSet<Colis>();
	
	@ManyToOne
	private Facture facture;
	
	@ManyToOne
	private UtilisateurXpr creerPar;
	
	@ManyToOne
	private UtilisateurXpr modifierPar;
	
	private String statut;
	
	private boolean disabled;
	
	@OneToMany(mappedBy = "bonRetour",fetch = FetchType.EAGER)
	private Set<Historique> historiques=new HashSet<Historique>();
	
	
	public BonRetour() {
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

	public Livreur getLivreur() {
		return livreur;
	}

	public void setLivreur(Livreur livreur) {
		this.livreur = livreur;
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

	public UtilisateurXpr getModifierPar() {
		return modifierPar;
	}

	public void setModifierPar(UtilisateurXpr modifierPar) {
		this.modifierPar = modifierPar;
	}
	
	
	
	
	
	
	

}
