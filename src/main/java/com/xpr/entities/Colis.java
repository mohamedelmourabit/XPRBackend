package com.xpr.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xpr.utils.Constants;

@Entity
@Table(name = "colis")
public class Colis implements Serializable {
	
	@Id
	@GenericGenerator(name = "colis_numCommande", strategy = "com.xpr.generator.ColisGenerator")
    @GeneratedValue(generator = "colis_numCommande")  
	private String numCommande;
	
	private String codeEnvoi;
	
	private String typeLivraison;
	
	// à crypter
	private String nomComplet;
	
	private double prix;
	
	@Temporal(TemporalType.DATE)
	private Date dateRamassage;
	
	@Temporal(TemporalType.DATE)
	private Date dateLivraison;
	
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	@Temporal(TemporalType.DATE)
	private Date dateModification;
	
	
	private boolean ouvertureColis;
	
	private boolean applicationFrais;
	
	private boolean applicationFraisAssurance;
	
	@Column(columnDefinition = "TEXT")
	private String remarque;

	//crypté
	private String destinataire;
	
	// telephone
	private String telephone;
	
	@ManyToOne
	private Ville villeDestination;
	
	private String secteur;
	
	private String adresse;
	
	private double crbt;
	
	@ManyToOne
	@JsonIgnore
	private Facture facture;
	
	@ManyToOne
	@JsonIgnore
	private BonLivraison bonLivraison;
	
	@ManyToOne
	@JsonIgnore
	private BonRamassage bonRamassage;
	
	
	@ManyToOne
	private Client client;
	
	@ManyToOne
	private Livreur livreur;
	
	@ManyToOne
	private Ramasseur ramasseur;
	
	private String statut;
	
	private boolean disabled;
		
	@ManyToOne
	private Business business;
	
	@ManyToOne
	private BonExpedition bonExpedition;
	
	@ManyToOne
	private BonRetour bonRetour;
	
	@OneToMany(mappedBy = "colis",fetch = FetchType.LAZY)
	private Set<Produit> produits=new HashSet<Produit>();
	
	@OneToMany(mappedBy = "colis",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Historique> historiques=new HashSet<Historique>();
	
	@OneToMany(mappedBy = "colis",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Commentaire> commentaires=new HashSet<Commentaire>();
	
	@ManyToOne
	private Agence agence;
	

	public Colis() {
		this.dateCreation = new Date();
		this.statut=Constants.NOUVEAU_COLIS;
	}

	public String getCodeEnvoi() {
		return codeEnvoi;
	}

	public void setCodeEnvoi(String codeEnvoi) {
		this.codeEnvoi = codeEnvoi;
	}

	public String getTypeLivraison() {
		return typeLivraison;
	}

	public void setTypeLivraison(String typeLivraison) {
		this.typeLivraison = typeLivraison;
	}

	public String getNomComplet() {
		return nomComplet;
	}

	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Date getDateRamassage() {
		return dateRamassage;
	}

	public void setDateRamassage(Date dateRamassage) {
		this.dateRamassage = dateRamassage;
	}

	public Date getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
	}


	public String getNumCommande() {
		return numCommande;
	}

	public void setNumCommande(String numCommande) {
		
		if(this.client!=null) {
			this.numCommande = this.client.getPrefixCommande()+"-"+numCommande;
		}else {
			this.numCommande = numCommande;
		}
	}

	public boolean isOuvertureColis() {
		return ouvertureColis;
	}

	public void setOuvertureColis(boolean ouvertureColis) {
		this.ouvertureColis = ouvertureColis;
	}

	public boolean isApplicationFrais() {
		return applicationFrais;
	}

	public void setApplicationFrais(boolean applicationFrais) {
		this.applicationFrais = applicationFrais;
	}

	public boolean isApplicationFraisAssurance() {
		return applicationFraisAssurance;
	}

	public void setApplicationFraisAssurance(boolean applicationFraisAssurance) {
		this.applicationFraisAssurance = applicationFraisAssurance;
	}


	public String getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getSecteur() {
		return secteur;
	}

	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public double getCrbt() {
		return crbt;
	}

	public void setCrbt(double crbt) {
		this.crbt = crbt;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public BonLivraison getBonLivraison() {
		return bonLivraison;
	}

	public void setBonLivraison(BonLivraison bonLivraison) {
		this.bonLivraison = bonLivraison;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Livreur getLivreur() {
		return livreur;
	}

	public void setLivreur(Livreur livreur) {
		this.livreur = livreur;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
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

	public Set<Produit> getProduits() {
		return produits;
	}

	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public BonExpedition getBonExpedition() {
		return bonExpedition;
	}

	public void setBonExpedition(BonExpedition bonExpedition) {
		this.bonExpedition = bonExpedition;
	}

	public BonRamassage getBonRamassage() {
		return bonRamassage;
	}

	public void setBonRamassage(BonRamassage bonRamassage) {
		this.bonRamassage = bonRamassage;
	}

	public Set<Historique> getHistoriques() {
		return historiques;
	}

	public void setHistoriques(Set<Historique> historiques) {
		this.historiques = historiques;
	}

	public Set<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(Set<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public Ville getVilleDestination() {
		return villeDestination;
	}

	public void setVilleDestination(Ville villeDestination) {
		this.villeDestination = villeDestination;
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	public BonRetour getBonRetour() {
		return bonRetour;
	}

	public void setBonRetour(BonRetour bonRetour) {
		this.bonRetour = bonRetour;
	}

	
	
	
	
	
	
		
	

}