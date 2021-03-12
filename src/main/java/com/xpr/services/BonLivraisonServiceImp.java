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

import com.xpr.dao.BonLivraisonRepository;
import com.xpr.dao.HistoriqueRepository;
import com.xpr.entities.BonLivraison;
import com.xpr.entities.Client;
import com.xpr.entities.Colis;
import com.xpr.entities.Historique;
import com.xpr.exceptions.BonLivraisonException;
import com.xpr.exceptions.ColisException;
import com.xpr.utils.Constants;

@Service
public class BonLivraisonServiceImp implements BonLivraisonService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BonLivraisonServiceImp.class);
	
	@Autowired
	private BonLivraisonRepository bonLivraisonRepository;
	
	@Autowired
	private HistoriqueRepository historiqueRepository;

	
	@Override
	public BonLivraison saveBonLivraison(BonLivraison bl) throws BonLivraisonException {
		LOGGER.info("Ajout d'un nouveau BL");
		
		if(bl.getColis().isEmpty()) {
			throw new BonLivraisonException("Erreur création BL sans colis");
		}
		
		bl.setDateCreation(new Date());
		bl.setStatut(Constants.EN_ATTENTE_RAMASSAGE);
		
		bl = bonLivraisonRepository.save(bl);
		
		Historique h =Historique.getHistorique("Ajout nouveau BL: "+bl.getNom(), bl.getStatut(), "cniTest");
		h.setBonLivraison(bl);
		bl.getHistoriques().add(h);
		
		return bl;
	}

	@Override
	public BonLivraison findBonLivraisonByNom(String nom) {
		LOGGER.info("Récupération du BL {}",nom );
		return bonLivraisonRepository.findById(nom).orElse(null);
	}

	@Override
	public Page<BonLivraison> findMyBonLivraisonByMc(String email, String mc, int page, int size) {
		LOGGER.info("Récupération du BL par mail= {} , mc= {}",email,mc );
		return bonLivraisonRepository.findByCniAndNomStartsWith(email,mc, PageRequest.of(page, size));
	}

	@Override
	public Page<BonLivraison> findAllBonLivraisonByMc(String mc, int page, int size) {
		return bonLivraisonRepository.findByNameStartsWith(mc, PageRequest.of(page, size));
	}

	@Override
	public List<BonLivraison> findAll() {
		return bonLivraisonRepository.findAll();
	}

	@Override
	public BonLivraison updateBonLivraison(String nom, BonLivraison bonLivraison) throws BonLivraisonException {
		LOGGER.info("Modification du BL {} ",nom);
		
		BonLivraison bl = bonLivraisonRepository.findById(nom).orElse(null);
		bonLivraison.setNom(nom);
		bonLivraison.setDateModification(new Date());
		if(!bonLivraison.getStatut().equals(Constants.NOUVEAU_BL) ) {
			throw new BonLivraisonException("Impossible de modifer un bl après ramassage");
		}
		
		if(bl.getStatut().equalsIgnoreCase(bonLivraison.getStatut())) {
			throw new BonLivraisonException("Impossible de modifier un bl avec le meme statut!");
		}
		
		bonLivraison = bonLivraisonRepository.save(bonLivraison);
		bonLivraison.setDateModification(new Date());
		Historique h =Historique.getHistorique("Modification BL: "+nom, bonLivraison.getStatut(), "cniTest");
		h.setBonLivraison(bonLivraison);
		bonLivraison.getHistoriques().add(h);
		
		
		return bonLivraison;
	}

	@Override
	public void deleteBonLivraison(String nom) throws BonLivraisonException {
		
		
		LOGGER.info("Suppression du BL {} ",nom);
		BonLivraison bl = bonLivraisonRepository.findById(nom).orElse(null);
		
		if(!bl.getStatut().equals(Constants.NOUVEAU_BL) ) {
			throw new BonLivraisonException("Impossible de supprimer un bl après ramassage");
		}
		bl.setDateModification(new Date());
		bl.setStatut(Constants.ANNULE);
		
		Historique h =Historique.getHistorique("Suppression BL: "+nom, bl.getStatut(), "cniTest");
		h.setBonLivraison(bl);
		bl.getHistoriques().add(h);
		bl.setDisabled(true);
		
		if(bl!=null) {
			bonLivraisonRepository.save(bl);
		}
	}

	@Override
	public BonLivraison generateBonLivraison(List<Colis> colis) {
		LOGGER.info("Generate nouveau BL avec {} colis ",colis.size());
		BonLivraison bl = new BonLivraison();
		bl.setDateCreation(new Date());
		if(colis!=null && colis.get(0)!=null) {
			Client client = colis.get(0).getClient();
			bl.setClient(client);
		}
		bl.setColis(new HashSet<Colis>(colis));
		bl.setDateModification(new Date());
		bl = bonLivraisonRepository.save(bl);
		Historique h =Historique.getHistorique("Ajout nouveau BL: "+bl.getNom(), bl.getStatut(), "cniTest");
		h.setBonLivraison(bl);
		bl.getHistoriques().add(h);
		
		return bl;
	}

	@Override
	public Page<BonLivraison> findAllBonLivraisonsByClient(String emailClient, int page, int size) {
		
		return bonLivraisonRepository.getAllBonLivraisonByClient(emailClient, PageRequest.of(page, size));
	}

	@Override
	public Page<BonLivraison> findAllBonLivraisonsByUtilisateurs(String emailUtilisateur, int page, int size) {
		
		return bonLivraisonRepository.getAllBonLivraisonsUtilisateur(emailUtilisateur, PageRequest.of(page, size));
	}
	
	@Override
	public int getCountBonLivraisons() {
		return bonLivraisonRepository.getCountBonLivraisons();
	}

	@Override
	public BonLivraison addColisToBonLivraison(String idBl, List<Colis> colis) throws BonLivraisonException {
		BonLivraison bl = findBonLivraisonByNom(idBl);
		bl.setDateModification(new Date());
		if(!bl.getStatut().equals(Constants.NOUVEAU_BL) ) {
			throw new BonLivraisonException("Impossible de modifier un bl après ramassage");
		}
		
		Historique h =Historique.getHistorique("Ajout " + colis.size()+" colis to BL: "+bl.getNom(), bl.getStatut(), "cniTest");
		h.setBonLivraison(bl);
		
		bl.getColis().addAll(colis);
		return bl;
	}

	@Override
	public BonLivraison deleteColisFomBonLivraison(String idBl, List<Colis> colis) {
		
		BonLivraison bl = findBonLivraisonByNom(idBl);
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
	public List<Colis> findColisFomBonLivraison(String idBl) {
		BonLivraison bl = findBonLivraisonByNom(idBl);
		return new ArrayList<Colis>(bl.getColis());
	}

	@Override
	public BonLivraison updateStatutBonLivraison(String nom, String statut) throws BonLivraisonException {
		LOGGER.info("Modification statut BL {} au statut {} ",nom,statut);
		
		BonLivraison bl = findBonLivraisonByNom(nom);
		
		bl.setDateModification(new Date());
		if(!bl.getStatut().equals(Constants.NOUVEAU_BL) ) {
			throw new BonLivraisonException("Impossible de modifier un bl après ramassage!");
		}
		
		if(bl.getStatut().equalsIgnoreCase(statut)) {
			throw new BonLivraisonException("Impossible de modifier un bl avec le meme statut!");
		}
		
		
		return bonLivraisonRepository.save(bl);
	}

	@Override
	public List<Historique> getHistoriqueBonLivraison(String nom) {
		
		return historiqueRepository.findHistoriqueBLByNom(nom);
	}

	@Override
	public Page<Historique> getHistoriqueBonLivraison(String nom, int page, int size) {
		
		return  historiqueRepository.findHistoriqueBLByNom(nom,PageRequest.of(page, size));
	}

	

}
