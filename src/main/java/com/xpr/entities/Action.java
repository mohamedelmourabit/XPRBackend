package com.xpr.entities;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


public class Action {
	
	@Id @GeneratedValue
	private long id;
	
	private Date dateCreation;
	
	private Date dateDebut;
	
	private Date dateFin;
	
	private String action;
	
	@ManyToOne
	private Utilisateur responsable;

}
