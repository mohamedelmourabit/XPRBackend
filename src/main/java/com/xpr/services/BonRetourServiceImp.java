package com.xpr.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xpr.dao.BonRetourRepository;
import com.xpr.dao.HistoriqueRepository;
import com.xpr.entities.BonRetour;
import com.xpr.entities.Client;
import com.xpr.entities.Colis;
import com.xpr.entities.Historique;
import com.xpr.exceptions.BonRetourException;
import com.xpr.utils.Constants;

@Service
public class BonRetourServiceImp implements BonRetourService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BonRetourServiceImp.class);
	
	@Autowired
	private BonRetourRepository bonRetourRepository;
	
	@Autowired
	private HistoriqueRepository historiqueRepository;

	
	@Override
	public BonRetour saveBonRetour(BonRetour brt) throws BonRetourException {
		LOGGER.info("Ajout d'un nouveau BRT");
		
		if(brt.getColis().isEmpty()) {
			throw new BonRetourException("Erreur création BRT sans colis");
		}
		
		brt.setDateCreation(new Date());
		brt.setStatut(Constants.NOUVEAU_BRT);
		
		brt = bonRetourRepository.save(brt);
		
		Historique h =Historique.getHistorique("Ajout nouveau BRT: "+brt.getNom(), brt.getStatut(), "cniTest");
		h.setBonRetour(brt);
		brt.getHistoriques().add(h);
		
		return brt;
	}

	@Override
	public BonRetour findBonRetourByNom(String nom) {
		LOGGER.info("Récupération du BR {}",nom );
		return bonRetourRepository.findByNom(nom);
	}

	@Override
	public Page<BonRetour> findMyBonRetourByMc(String email, String mc, int page, int size) {
		LOGGER.info("Récupération du BR par mail= {} , mc= {}",email,mc );
		return bonRetourRepository.findByCniAndNomStartsWith(email,mc, PageRequest.of(page, size));
	}

	@Override
	public Page<BonRetour> findAllBonRetourByMc(String mc, int page, int size) {
		return bonRetourRepository.findByNameStartsWith(mc, PageRequest.of(page, size));
	}

	@Override
	public List<BonRetour> findAll() {
		return bonRetourRepository.findAll();
	}

	@Override
	public BonRetour updateBonRetour(String nom, BonRetour bonRetour) throws BonRetourException {
		LOGGER.info("Modification du BRT {} ",nom);
		
		BonRetour bl = bonRetourRepository.findByNom(nom);
		bonRetour.setNom(nom);
		bonRetour.setDateModification(new Date());
		if(!bonRetour.getStatut().equals(Constants.NOUVEAU_BRT) ) {
			throw new BonRetourException("Impossible de modifer un brt après ramassage");
		}
		
		if(bl.getStatut().equalsIgnoreCase(bonRetour.getStatut())) {
			throw new BonRetourException("Impossible de modifier un brt avec le meme statut!");
		}
		
		bonRetour = bonRetourRepository.save(bonRetour);
		bonRetour.setDateModification(new Date());
		Historique h =Historique.getHistorique("Modification BRT: "+nom, bonRetour.getStatut(), "cniTest");
		h.setBonRetour(bonRetour);
		bonRetour.getHistoriques().add(h);
		
		
		return bonRetour;
	}

	@Override
	public void deleteBonRetour(String nom) throws BonRetourException {
		
		
		LOGGER.info("Suppression du BR {} ",nom);
		BonRetour bl = bonRetourRepository.findByNom(nom);
		
		if(!bl.getStatut().equals(Constants.NOUVEAU_BR) ) {
			throw new BonRetourException("Impossible de supprimer un bl après ramassage");
		}
		bl.setDateModification(new Date());
		bl.setStatut(Constants.ANNULE);
		
		Historique h =Historique.getHistorique("Suppression BR: "+nom, bl.getStatut(), "cniTest");
		h.setBonRetour(bl);
		bl.getHistoriques().add(h);
		bl.setDisabled(true);
		
		if(bl!=null) {
			bonRetourRepository.save(bl);
		}
	}

	@Override
	public BonRetour generateBonRetour(List<Colis> colis) {
		LOGGER.info("Generate nouveau BRT avec {} colis ",colis.size());
		BonRetour bl = new BonRetour();
		bl.setDateCreation(new Date());
		if(colis!=null && colis.get(0)!=null) {
			Client client = colis.get(0).getClient();
			bl.setClient(client);
		}
		bl.setColis(new HashSet<Colis>(colis));
		bl.setDateModification(new Date());
		bl = bonRetourRepository.save(bl);
		Historique h =Historique.getHistorique("Ajout nouveau BR: "+bl.getNom(), bl.getStatut(), "cniTest");
		h.setBonRetour(bl);
		bl.getHistoriques().add(h);
		
		return bl;
	}

	@Override
	public Page<BonRetour> findAllBonRetoursByClient(String emailClient, int page, int size) {
		
		return bonRetourRepository.getAllBonRetourByClient(emailClient, PageRequest.of(page, size));
	}

	@Override
	public Page<BonRetour> findAllBonRetoursByUtilisateurs(String emailUtilisateur, int page, int size) {
		
		return bonRetourRepository.getAllBonRetoursUtilisateur(emailUtilisateur, PageRequest.of(page, size));
	}
	
	@Override
	public int getCountBonRetours() {
		return bonRetourRepository.getCountBonRetours();
	}

	@Override
	public BonRetour addColisToBonRetour(String idBr, List<Colis> colis) throws BonRetourException {
		BonRetour bl = findBonRetourByNom(idBr);
		bl.setDateModification(new Date());
		if(!bl.getStatut().equals(Constants.NOUVEAU_BR) ) {
			throw new BonRetourException("Impossible de modifier un bl après ramassage");
		}
		
		Historique h =Historique.getHistorique("Ajout " + colis.size()+" colis to BR: "+bl.getNom(), bl.getStatut(), "cniTest");
		h.setBonRetour(bl);
		
		bl.getColis().addAll(colis);
		return bl;
	}

	@Override
	public BonRetour deleteColisFomBonRetour(String idBr, List<Colis> colis) {
		
		BonRetour bl = findBonRetourByNom(idBr);
		bl.setDateModification(new Date());
		Iterator<Colis> iterator = bl.getColis().iterator();
		while (iterator.hasNext()) {
		    Colis element = iterator.next();
		    for(Colis c : colis) {
		    	if(c.getNumCommande().equals(element.getNumCommande())) {
		    		iterator.remove();
		    	}
		    }
		}
		
		return bl;
	}

	@Override
	public List<Colis> findColisFomBonRetour(String idBl) {
		BonRetour bl = findBonRetourByNom(idBl);
		return new ArrayList<Colis>(bl.getColis());
	}

	@Override
	public BonRetour updateStatutBonRetour(String nom, String statut) throws BonRetourException {
		LOGGER.info("Modification statut BRT {} au statut {} ",nom,statut);
		
		BonRetour br = findBonRetourByNom(nom);
		
		br.setDateModification(new Date());
		if(!br.getStatut().equals(Constants.NOUVEAU_BRT) ) {
			throw new BonRetourException("Impossible de modifier un brt après ramassage!");
		}
		
		if(br.getStatut().equalsIgnoreCase(statut)) {
			throw new BonRetourException("Impossible de modifier un brt avec le meme statut!");
		}
		
		
		return bonRetourRepository.save(br);
	}

	@Override
	public List<Historique> getHistoriqueBonRetour(String nom) {
		return historiqueRepository.findHistoriqueBonRetourByNom(nom);
	}

	@Override
	public Page<Historique> getHistoriqueBonRetour(String nom, int page, int size) {
		return historiqueRepository.findHistoriqueBonRetourByNom(nom,PageRequest.of(page, size));
	}

	

}
