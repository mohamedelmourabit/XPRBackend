package com.xpr.services;

import java.util.List;
import com.xpr.entities.Autorisation;
import com.xpr.entities.Profile;
import com.xpr.entities.Utilisateur;



public interface AccountService {
	
	public Utilisateur saveUtilisateur(Utilisateur appUser);
	
	public Utilisateur findUtilisateurByEmail(String email);
	
	public Utilisateur findUtilisateurByCni(String email);
	
	public void addAuthorisationToProfile(String authorisation,String profileName);
	
	public void addProfileToUtilisateur(String cni,long profileId);
	
	public List<Profile> findProfilesByUsers(String cni);
	
	public Autorisation saveAuthorisation(String authorisationName);
	
	public void addAuthorisationToUser(String authorisation,String email);

	public List<Autorisation> findAutorisationUtilisateur(String cni);
	
	public List<Autorisation> findAutorisationByProfile(String profile);

}
