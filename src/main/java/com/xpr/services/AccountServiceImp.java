package com.xpr.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xpr.dao.AutorisationRepository;
import com.xpr.dao.ProfileRepository;
import com.xpr.dao.UtilisateurRepository;
import com.xpr.entities.Autorisation;
import com.xpr.entities.Profile;
import com.xpr.entities.Utilisateur;



@Service
@Transactional
public class AccountServiceImp implements AccountService {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImp.class);
	
	
	@Autowired
	private UtilisateurRepository userRepository;
	
	@Autowired
	private AutorisationRepository authRepository;
	
	@Autowired
	private ProfileRepository profileRepository;

	
	@Override
	public Utilisateur findUtilisateurByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Utilisateur saveUtilisateur(Utilisateur appUser) {
		return userRepository.save(appUser);
	}


	@Override
	public Autorisation saveAuthorisation(String authorisationName) {
		return authRepository.save(new Autorisation(null,authorisationName));
	}

	@Override
	public void addAuthorisationToUser(String authorisation, String cni) {
		Utilisateur user = userRepository.findByCni(cni);
		Autorisation authorisation2 = authRepository.findByAuthName(authorisation);
		user.getAuthorities().add(authorisation2);
		
	}
	
	
	@Override
	public List<Autorisation> findAutorisationUtilisateur(String cni) {
		Utilisateur user = userRepository.findByCni(cni);
		Set<Autorisation> autorisations = new HashSet<Autorisation>();
		
		for(Profile profile : findProfilesByUsers(cni)) {
        	for(Autorisation auth : findAutorisationByProfile(profile.getPrflName())) {
        		autorisations.add(auth);
        	}
        }
        
        for(Autorisation auth : user.getAuthorities()) {
        	if(!autorisations.contains(auth)) {
        		autorisations.add(auth);
        	}
        }
		return userRepository.findUserAuthority(cni);
	}

	@Override
	public Utilisateur findUtilisateurByCni(String cni) {
		
		return userRepository.findByCni(cni);
	}

	@Override
	public void addAuthorisationToProfile(String authorisation, String profileName) {
		
		Profile p = profileRepository.findByPrflName(profileName);
		Autorisation auth=authRepository.findByAuthName(authorisation);
		p.getAuthorities().add(auth);
		
	}

	@Override
	public List<Profile> findProfilesByUsers(String cni) {
		
		return profileRepository.findProfilesByUtilisateur(cni);
	}

	@Override
	public void addProfileToUtilisateur(String cni, long profileId) {
		
		Utilisateur user = userRepository.findByCni(cni);
		Profile p = new Profile();
		p.setId(profileId);
		user.getProfiles().add(p);
		
	}

	@Override
	public List<Autorisation> findAutorisationByProfile(String profile) {
		
		
		Profile p = profileRepository.findByPrflName(profile);
		
			
		return new ArrayList<Autorisation>(p.getAuthorities());
	}

	
		
	

}
