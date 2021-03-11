package com.xpr.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorisations")
public class Autorisation implements Serializable {
	
	@Id @GeneratedValue
	private Long idAuth;
	
	private String authName;
	
	
	public Autorisation() {
		
	}
	
	

	public Autorisation(Object object, String authorisationName) {
		this.authName=authorisationName;
	}



	public Long getIdAuth() {
		return idAuth;
	}



	public void setIdAuth(Long idAuth) {
		this.idAuth = idAuth;
	}



	public String getAuthName() {
		return authName;
	}



	public void setAuthName(String authName) {
		this.authName = authName;
	}



	@Override
	public String toString() {
		return "Authorisation [idAuth=" + idAuth + ", authName=" + authName + "]";
	}
	
	
	
}
