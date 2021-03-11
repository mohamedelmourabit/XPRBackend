package com.xpr.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.xpr.dto.RegisterForm;
import com.xpr.entities.Autorisation;
import com.xpr.entities.Utilisateur;
import com.xpr.services.AccountService;

@RestController
public class AccountRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountRestController.class);
	
	@Autowired
	private AccountService accountService;
	
		@PostMapping("/register")
		public Utilisateur register(@RequestBody RegisterForm userForm) {
			if(!userForm.getPassword().equals(userForm.getRepassword())) throw new RuntimeException("You must confirm your password");
			
			Utilisateur user = accountService.findUtilisateurByEmail(userForm.getEmail());
			if(user !=null) throw new RuntimeException("This user already exists");
			Utilisateur utilisateur = new Utilisateur(userForm.getEmail(),userForm.getPassword());
			
			accountService.saveUtilisateur(utilisateur);
			//accountService.addProfileToUser(utilisateur.getEmail(),"");
			return utilisateur;
		}
		
		@PostMapping("/autorisation")
		public Autorisation addAutorisation(@RequestBody String autorisationName) {
			
			return accountService.saveAuthorisation(autorisationName);
			
		}
		
		@PostMapping("/autorisationToUser")
		public Utilisateur addAutorisation(@RequestParam(name="cni") String cni,@RequestParam(name="autorisation") String autorisation) {
			
			Utilisateur user = accountService.findUtilisateurByCni(cni);
		
				if(user!=null) {
					accountService.addAuthorisationToUser(autorisation, cni);
				List<Autorisation> autorisations = getAuthorisation(cni);
				user.setAuthorities(new HashSet<Autorisation>(autorisations));
			}else {
				throw new RuntimeException("Utilisateur introuvable !");
			}
			
			return user;
			
		}
		
		
		@PostMapping("/profileToUser")
		public Utilisateur addProfileToUser(@RequestParam(name="cni") String cni,@RequestParam(name="profileId") Long profileId) {
			
			Utilisateur user = accountService.findUtilisateurByCni(cni);
		
				if(user!=null) {
					accountService.addProfileToUtilisateur(cni, profileId);
					
			}else {
				throw new RuntimeException("Utilisateur introuvable !");
			}
			
			return user;
			
		}
		
		@PostMapping("/logging")
		public Utilisateur checkConnection(@RequestParam(name="cni") String cni,@RequestParam(name="password") String password) {
			
			Utilisateur user = accountService.findUtilisateurByCni(cni);
			
			if(user!=null && user.getPassword().equals(password)) {
				return user;
			}else {
				throw new RuntimeException("Email où Mot de passe incorrecte !");
			}
			
		}
		
		@PostMapping("/authorisations")
		public List<Autorisation> getAuthorisation(@RequestParam(name="cni") String cni) {
			
			Set<Autorisation> autorisations = new HashSet<Autorisation>();
			
			Utilisateur user = accountService.findUtilisateurByCni(cni);
			
			if(user!=null) {
				
				autorisations.addAll(accountService.findAutorisationUtilisateur(cni));
				
				return new ArrayList<Autorisation>(autorisations);
			}else {
				throw new RuntimeException("Utilisateur introuvable !");
			}
			
			
			
		}
		
		@PostMapping("/authorisationsByProfile")
		public List<Autorisation> getAuthorisationByProfile(@RequestParam(name="profile") String profile) {
			
			return accountService.findAutorisationByProfile(profile);
			
			
			
		}
	

}
