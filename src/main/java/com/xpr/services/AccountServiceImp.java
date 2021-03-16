package com.xpr.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xpr.dao.AutorisationRepository;
import com.xpr.dao.ProfileRepository;
import com.xpr.dao.ServiceRepository;
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
	
	@Autowired
	private ServiceRepository serviceRepository;

	
	@Override
	public Utilisateur findUtilisateurByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Utilisateur saveUtilisateur(Utilisateur appUser) {
		appUser.setPassword(bCryptPasswordEncoder()
		           .encode(appUser.getPassword()));
		return userRepository.save(appUser);
	}


	@Override
	public Autorisation saveAuthorisation(String authorisationName) {
		return authRepository.save(new Autorisation(null,authorisationName));
	}

	@Override
	public Utilisateur addAuthorisationToUser(String authorisation, String cni) {
		Utilisateur user = userRepository.findByCni(cni);
		Autorisation authorisation2 = authRepository.findByAuthName(authorisation);
		user.getAuthorities().add(authorisation2);
		
		return user;
		
	}
	
	
	@Override
	public List<Autorisation> findAutorisationUtilisateur(String cni) {
		Utilisateur user = userRepository.findByCni(cni);
		Set<Autorisation> autorisations = new HashSet<Autorisation>();
		
		System.out.println(findProfilesByUsers(cni));
		
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
		return new ArrayList<Autorisation>(autorisations);
	}

	@Override
	public Utilisateur findUtilisateurByCni(String cni) {
		
		return userRepository.findByCni(cni);
	}

	@Override
	public Profile addAuthorisationToProfile(String authorisation, String profileName) {
		
		Profile p = profileRepository.findByPrflName(profileName);
		Autorisation auth=authRepository.findByAuthName(authorisation);
		p.getAuthorities().add(auth);
		return p;
		
	}

	@Override
	public List<Profile> findProfilesByUsers(String cni) {
		
		Utilisateur user = userRepository.findByCni(cni);
		return new ArrayList<Profile>(user.getProfiles());
	}

	@Override
	public Utilisateur addProfileToUtilisateur(String cni, long profileId) {
		
		Utilisateur user = userRepository.findByCni(cni);
		Profile p = new Profile();
		p.setId(profileId);
		user.getProfiles().add(p);
		
		return user;
		
	}

	@Override
	public List<Autorisation> findAutorisationByProfile(String profile) {
		
		
		Profile p = profileRepository.findByPrflName(profile);
		
			
		return new ArrayList<Autorisation>(p.getAuthorities());
	}

	@Override
	public Utilisateur removeAuthorisationToUtilisateur(String cni, String autorisation) {
		
		Utilisateur user = userRepository.findByCni(cni);
		
		user.getAuthorities().removeIf(aut->aut.getAuthName().equalsIgnoreCase(autorisation));
		
		return user;

	}

	@Override
	public Utilisateur removeProfileToUtilisateur(String cni, long profileId) {
		Utilisateur user = userRepository.findByCni(cni);
		
		user.getProfiles().removeIf(prfl->prfl.getId()==profileId);
		
		return user;

		
	}

	@Override
	public Utilisateur addUtilisateurToServie(String cni, long serviceId) {
		
		Utilisateur user = userRepository.findByCni(cni);
		
		com.xpr.entities.Service service = serviceRepository.findById(serviceId).orElse(null) ;
		
		user.setService(service);
		
		userRepository.save(user);
		
		return user;
		
	}

	@Override
	public Utilisateur removeUtilisateurServie(String cni, long serviceId) {
		
		Utilisateur user = userRepository.findByCni(cni);
		
		com.xpr.entities.Service c = serviceRepository.findById(serviceId).orElse(null);
		
		
		if(user.getService().getId()!=serviceId) {
			throw new IllegalArgumentException("l'utilisateur "+ cni+ " n'est pas affecté au service "+(c!=null?c.getNom():serviceId));
		}
		
		user.setService(null);
		
		return userRepository.save(user);
		
		
	}

	@Override
	public com.xpr.entities.Service saveService(com.xpr.entities.Service service) {
		
		return serviceRepository.save(service);
	}

	@Override
	public com.xpr.entities.Service getServices(String nomService) {
		
		return serviceRepository.findByNom(nomService);
	}

	@Override
	public List<com.xpr.entities.Service> getServices() {
		
		return serviceRepository.findAll();
	}

	@Override
	public void deleteService(Long idService) {
		serviceRepository.deleteById(idService);
		
	}

	@Override
	public List<Profile> getAllProfiles() {
		return profileRepository.findAll();
	}

	@Override
	public List<Autorisation> getAllAutorisations() {
		return authRepository.findAll();
	}

	@Override
	public Profile addProfile(Profile profile) {
		return profileRepository.save(profile);
	}

	@Override
	public Page<Autorisation> getAllAutorisations(int page, int size) {

		return authRepository.getAllAutorisations(PageRequest.of(page, size));
	}

	@Override
	public Page<Autorisation> findAutorisationByProfile(String profile, int page, int size) {
		return profileRepository.findAuthoritiesByPrflName2(profile, PageRequest.of(page, size));
	}
	
	
	@Bean public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder(); 
	}

	
		
	

}
