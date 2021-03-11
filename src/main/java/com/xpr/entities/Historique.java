package com.xpr.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "historiques")
public class Historique implements Serializable  {
	
	@Id @GeneratedValue
	private Long id;
	
	private String action;
	
	private String statut;
	
	private Date dateCreation;
	
	@ManyToOne
	private Utilisateur utilisateur;
	
	@ManyToOne
	@JsonIgnore
	private Colis colis;
	
	@ManyToOne
	@JsonIgnore
	private Demande demande;
	
	
	@ManyToOne
	@JsonIgnore
	private BonLivraison bonLivraison;
	
	@ManyToOne
	@JsonIgnore
	private BonRamassage bonRamassage;
	
	@ManyToOne
	@JsonIgnore
	private BonRetour bonRetour;
	
	@ManyToOne
	@JsonIgnore
	private BonExpedition bonExpedition;
	
	public Historique() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Colis getColis() {
		return colis;
	}

	public void setColis(Colis colis) {
		this.colis = colis;
	}

	public Demande getDemande() {
		return demande;
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
	}

	public BonLivraison getBonLivraison() {
		return bonLivraison;
	}

	public void setBonLivraison(BonLivraison bonLivraison) {
		this.bonLivraison = bonLivraison;
	}

	public BonRamassage getBonRamassage() {
		return bonRamassage;
	}

	public void setBonRamassage(BonRamassage bonRamassage) {
		this.bonRamassage = bonRamassage;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public BonRetour getBonRetour() {
		return bonRetour;
	}

	public void setBonRetour(BonRetour bonRetour) {
		this.bonRetour = bonRetour;
	}
	
	
	public static Historique getHistorique(String action,String statut,String cniUtilisateur) {
		Historique historique = new Historique();
		historique.setAction(action);
		historique.setStatut(statut);
		historique.setDateCreation(new Date());
		Utilisateur user = new Utilisateur(cniUtilisateur);
		historique.setUtilisateur(user);
		return historique;
		
	}
	

}
