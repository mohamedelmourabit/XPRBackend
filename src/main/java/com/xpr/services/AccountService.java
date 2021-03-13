package com.xpr.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.xpr.entities.Autorisation;
import com.xpr.entities.Profile;
import com.xpr.entities.Service;
import com.xpr.entities.Utilisateur;



public interface AccountService {
	
	public Utilisateur saveUtilisateur(Utilisateur appUser);
	
	public Utilisateur findUtilisateurByEmail(String email);
	
	public Utilisateur findUtilisateurByCni(String cni);
	
	public Profile addAuthorisationToProfile(String authorisation,String profileName);
	
	public Utilisateur removeAuthorisationToUtilisateur(String cni,String authorisation);
	
	public Utilisateur addProfileToUtilisateur(String cni,long profileId);
	
	public Utilisateur removeProfileToUtilisateur(String cni,long profileId);
	
	public List<Profile> findProfilesByUsers(String cni);
	
	public List<Profile> getAllProfiles();
	
	public List<Autorisation> getAllAutorisations();
	
	public Page<Autorisation> getAllAutorisations(int page,int size);
	
	public Page<Autorisation> findAutorisationByProfile(String profile,int page,int size);
	
	public Profile addProfile(Profile profile);
	
	public Autorisation saveAuthorisation(String authorisationName);
	
	public Utilisateur addAuthorisationToUser(String authorisation,String email);

	public List<Autorisation> findAutorisationUtilisateur(String cni);
	
	public List<Autorisation> findAutorisationByProfile(String profile);
	
	public Utilisateur addUtilisateurToServie(String cni,long serviceId);
	
	public Utilisateur removeUtilisateurServie(String cni,long serviceId);
	
	
	public Service saveService(Service service);
	
	public Service getServices(String nomService);
	
	public List<Service> getServices();
	
	public void deleteService(Long idService);
	
	
	
	

}
