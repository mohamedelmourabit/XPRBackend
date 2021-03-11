package com.xpr.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "produits")
public class Produit implements Serializable  {
	
	@Id
	private String id;
	
	private String idIntern;
	
	private String nom;
	
	private String reference;
	
	private String nature;
	
	private String sku;
	
	private String dimension;
	
	private Double prixOriginale;
	
	private Double prixVente;
	
	private Integer qte;
	
	private boolean emballer;
	
	private String photo;
	
	private boolean containVariantes;
	
	@OneToMany(mappedBy = "produit",fetch = FetchType.LAZY)
	private Set<Variante> variantes=new HashSet<>();
	
	@OneToMany(mappedBy = "produit",fetch = FetchType.LAZY)
	private Set<HistoriqueStock> historiqueStock=new HashSet<>();
	
	@OneToMany(mappedBy = "produit",fetch = FetchType.LAZY)
	private Set<Stock> stocks=new HashSet<>();
	
	@ManyToOne
	private Client client;
	
	
	@ManyToMany
	private Set<Colis> colis=new HashSet<Colis>();
	

	 public Produit() {
		
	 }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getIdIntern() {
		return idIntern;
	}


	public void setIdIntern(String idIntern) {
		this.idIntern = idIntern;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getReference() {
		return reference;
	}


	public void setReference(String reference) {
		this.reference = reference;
	}


	public String getSku() {
		return sku;
	}


	public void setSku(String sku) {
		this.sku = sku;
	}


	public String getDimension() {
		return dimension;
	}


	public void setDimension(String dimension) {
		this.dimension = dimension;
	}


	public double getPrixOriginale() {
		return prixOriginale;
	}


	public void setPrixOriginale(double prixOriginale) {
		this.prixOriginale = prixOriginale;
	}


	public double getPrixVente() {
		return prixVente;
	}


	public void setPrixVente(double prixVente) {
		this.prixVente = prixVente;
	}


	public int getQte() {
		return qte;
	}


	public void setQte(int qte) {
		this.qte = qte;
	}


	public boolean isEmballer() {
		return emballer;
	}


	public void setEmballer(boolean emballer) {
		this.emballer = emballer;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public boolean isContainVariantes() {
		return containVariantes;
	}


	public void setContainVariantes(boolean containVariantes) {
		this.containVariantes = containVariantes;
	}


	public Set<Variante> getVariantes() {
		return variantes;
	}


	public void setVariantes(Set<Variante> variantes) {
		this.variantes = variantes;
	}


	


	public Set<HistoriqueStock> getHistoriqueStock() {
		return historiqueStock;
	}


	public void setHistoriqueStock(Set<HistoriqueStock> historiqueStock) {
		this.historiqueStock = historiqueStock;
	}


	public void setPrixOriginale(Double prixOriginale) {
		this.prixOriginale = prixOriginale;
	}


	public void setPrixVente(Double prixVente) {
		this.prixVente = prixVente;
	}


	public void setQte(Integer qte) {
		this.qte = qte;
	}


	public Set<Colis> getColis() {
		return colis;
	}


	public void setColis(Set<Colis> colis) {
		this.colis = colis;
	}


	public String getNature() {
		return nature;
	}


	public void setNature(String nature) {
		this.nature = nature;
	}


	public Set<Stock> getStocks() {
		return stocks;
	}


	public void setStocks(Set<Stock> stocks) {
		this.stocks = stocks;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}
	 
	
	 
	
	

}
