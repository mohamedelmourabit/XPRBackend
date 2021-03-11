package com.xpr.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name="bonExpeditions")
public class BonExpedition implements Serializable  {
	
	@Id @GeneratedValue
	private Long id;
		
	private String statut;
	
	private String idBon;
	
	private String logistique;
	
	private String refBonLogistique;
	
	private Date dateCreation;
	
	@ManyToOne
	private Agence depart;
	
	@ManyToOne
	private Agence destination;
	
	@OneToMany(mappedBy = "bonExpedition")
	private Set<Colis> colis=new HashSet<>();
	
	@OneToMany(mappedBy = "bonExpedition",fetch = FetchType.LAZY)
	private Set<Historique> historiques=new HashSet<Historique>();
	
	@ManyToOne
	private Utilisateur creerPar;
	
	private boolean disabled;
	
	public BonExpedition() {
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


	public String getIdBon() {
		return idBon;
	}

	public void setIdBon(String idBon) {
		this.idBon = idBon;
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

	
	
	

}
