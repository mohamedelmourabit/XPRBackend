package com.xpr.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "clients")
public class Client  implements Serializable {
	
	@Id
	private String cni;
	private String ice;
	private String nom;
	private String contact;
	private String telephone;
	private String prefixCommande;
	private String address;
	private String email;
	private boolean disabled;
	
	//Particulier ou Entreprise
	private String typeClient;
	
	@OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Colis> colis = new HashSet<Colis>();
	
	@OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Facture> factures= new HashSet<Facture>();
	
	@OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<BonLivraison> bonLivraisons = new HashSet<BonLivraison>();
	
	@OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<BonRetour> bonRetours = new HashSet<BonRetour>();
	
	@OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<BonRamassage> bonRamassages = new HashSet<BonRamassage>();

	@OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Business> bussiness = new HashSet<Business>();
	
	@OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<UtilisateurXpr> utilisateurXprs = new HashSet<UtilisateurXpr>();
	
	@OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Stock> stocks = new HashSet<Stock>();
	
	@ManyToOne
	private Ville ville;

	public Client() {
		
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getContact() {
		return contact;
	}



	public void setContact(String contact) {
		this.contact = contact;
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



	public Set<Facture> getFactures() {
		return factures;
	}



	public void setFactures(Set<Facture> factures) {
		this.factures = factures;
	}



	public Set<BonLivraison> getBonLivraisons() {
		return bonLivraisons;
	}



	public void setBonLivraisons(Set<BonLivraison> bonLivraisons) {
		this.bonLivraisons = bonLivraisons;
	}



	public String getPrefixCommande() {
		return prefixCommande;
	}



	public void setPrefixCommande(String prefixCommande) {
		this.prefixCommande = prefixCommande;
	}



	public String getCni() {
		return cni;
	}



	public void setCni(String cni) {
		this.cni = cni;
	}



	public String getIce() {
		return ice;
	}



	public void setIce(String ice) {
		this.ice = ice;
	}



	public String getTypeClient() {
		return typeClient;
	}



	public void setTypeClient(String typeClient) {
		this.typeClient = typeClient;
	}



	public Set<Business> getBussiness() {
		return bussiness;
	}



	public void setBussiness(Set<Business> bussiness) {
		this.bussiness = bussiness;
	}



	public Set<UtilisateurXpr> getUtilisateurXprs() {
		return utilisateurXprs;
	}



	public void setUtilisateurXprs(Set<UtilisateurXpr> utilisateurXprs) {
		this.utilisateurXprs = utilisateurXprs;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public boolean isDisabled() {
		return disabled;
	}



	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public Set<BonRetour> getBonRetours() {
		return bonRetours;
	}



	public void setBonRetours(Set<BonRetour> bonRetours) {
		this.bonRetours = bonRetours;
	}



	public Set<BonRamassage> getBonRamassages() {
		return bonRamassages;
	}



	public void setBonRamassages(Set<BonRamassage> bonRamassages) {
		this.bonRamassages = bonRamassages;
	}



	public Set<Stock> getStocks() {
		return stocks;
	}



	public void setStocks(Set<Stock> stocks) {
		this.stocks = stocks;
	}



	public Ville getVille() {
		return ville;
	}



	public void setVille(Ville ville) {
		this.ville = ville;
	}
	
	
	
	

	
	
	
}
