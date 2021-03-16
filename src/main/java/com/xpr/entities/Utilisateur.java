package com.xpr.entities;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;


@Entity
@Table(name = "utilisateurs")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "typeUtilisateur", discriminatorType = DiscriminatorType.STRING, length = 25)

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typeUtilisateur")
@JsonSubTypes({ @Type(name = "Livreur", value = Livreur.class),
		@Type(name = "Utilisateur", value = Client.class)
		})

public class Utilisateur implements Serializable {
	
	@Id
	private String cni;
	
	private String email;
	
	private String password;
	
	@ManyToOne
	private Service service;
	
		
	public Utilisateur(String cni) {
		super();
		this.cni = cni;
	}

	public Utilisateur(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Autorisation> authorities = new HashSet<>();
	
	@ManyToMany
	@JsonIgnore
	private Set<Profile> profiles = new HashSet<>();
	
	
	private boolean disabled;
	
	@Column(name = "typeUtilisateur", insertable = false, updatable = false)
	protected String typeUtilisateur;
	
	
	public Utilisateur() {
		this.typeUtilisateur="Utilisateur";
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}
	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}

	

	public Set<Autorisation> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Autorisation> authorities) {
		this.authorities = authorities;
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

	public String getCni() {
		return cni;
	}

	public void setCni(String cni) {
		this.cni = cni;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}


	public String getTypeUtilisateur() {
		return typeUtilisateur;
	}

	public void setTypeUtilisateur(String typeUtilisateur) {
		this.typeUtilisateur = typeUtilisateur;
	}

	public Set<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(Set<Profile> profiles) {
		this.profiles = profiles;
	}
	
	
	
	

}
