package com.xpr.entities;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "profiles")
public class Profile implements Serializable  {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String prflName;

	
	@ManyToMany
	private Set<Autorisation> authorities = new HashSet<>();
	
	
	
	public Profile() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getPrflName() {
		return prflName;
	}



	public void setPrflName(String prflName) {
		this.prflName = prflName;
	}




	public Set<Autorisation> getAuthorities() {
		return authorities;
	}



	public void setAuthorities(Set<Autorisation> authorities) {
		this.authorities = authorities;
	}



	
}
