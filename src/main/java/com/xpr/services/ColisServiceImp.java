package com.xpr.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.xpr.dao.ColisRepository;
import com.xpr.dao.CommentaireRepository;
import com.xpr.dao.HistoriqueRepository;
import com.xpr.dao.LivreurRepository;
import com.xpr.entities.Colis;
import com.xpr.entities.Commentaire;
import com.xpr.entities.Historique;
import com.xpr.entities.Livreur;
import com.xpr.exceptions.ColisException;
import com.xpr.exceptions.LivreurException;
import com.xpr.utils.Constants;

@Service
public class ColisServiceImp implements ColisService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ColisServiceImp.class);
	
	@Autowired
	private ColisRepository colisRepository;
	

	@Autowired
	private LivreurRepository livreurRepository;
	
	@Autowired
	private HistoriqueRepository historiqueRepository;
		
	@Autowired
	private CommentaireRepository commentaireRepository;
	
	@Override
	public Colis saveColis(Colis colis) {
		LOGGER.info("Ajout d'un nouveau colis " , colis.getNumCommande());
		String codeEnvoie = generateCodeEnvoie();
		colis.setCodeEnvoi(codeEnvoie);
		colis.setDateCreation(new Date());
		colis.setStatut(Constants.NOUVEAU_COLIS);
		colis = colisRepository.save(colis);
		
		Historique h =Historique.getHistorique("Ajout d'un nouveau colis: "+colis.getNumCommande(), colis.getStatut(), "cniTest");
		h.setColis(colis);
		
		colis.getHistoriques().add(h);
		
		historiqueRepository.save(h);
		//generate ticket 
		return  colis;
	}

	@Override
	public Colis findColisById(String numCommande) {
		LOGGER.info("get colis by numCommande {} " , numCommande);
		return colisRepository.findById(numCommande).get();
	}

	@Override
	public Page<Colis> findAllColisByMc(String mc, int page, int size) {
		LOGGER.info("get colis by mot clé {} " , mc);
		return colisRepository.findByNameStartsWith("%"+mc+"%", PageRequest.of(page, size));
	}

	@Override
	public List<Colis> findAll() {
		LOGGER.info("Recupération de tous les colis");
		return colisRepository.findAll();
	}

	@Override
	public Colis updateColis(String numCommande, Colis colis) throws ColisException {
		 LOGGER.info("Modification colis {}" , numCommande);
		 Colis colis1 = findColisById(numCommande);
		 
		 if(colis.getStatut().equalsIgnoreCase(colis1.getStatut())) {
			 throw new ColisException("Veuillez changé le statut du colis !");
		}
		 
		 if(colis1.getStatut().equals(Constants.NOUVEAU_COLIS)  || colis1.getStatut().equals(Constants.EN_ATTENTE_RAMASSAGE)   )  {
			 colis.setNumCommande(numCommande);
			 colis.setDateModification(new Date());
			return colisRepository.save(colis);
		 }else {
			 throw new ColisException("Modification du colis interdit arpès ramassage ");
		 }
		 
		 
	}
	
	@Override
	public Colis updateStatutColis(String numCommande, String statut) throws ColisException {
		Colis colis = findColisById(numCommande);
		
		if(colis.getStatut().equalsIgnoreCase(statut)) {
			 throw new ColisException("Veuillez changé le statut du colis !");
		}
		
		
		if(colis.getStatut().equals(Constants.NOUVEAU_COLIS)  || colis.getStatut().equals(Constants.EN_ATTENTE_RAMASSAGE)   )  {
			 colis.setNumCommande(numCommande);
			 colis.setDateModification(new Date());
			colis= colisRepository.save(colis);
			Historique h =Historique.getHistorique("Modification statut du colis: "+colis.getNumCommande(), colis.getStatut(), "cniTest");
			h.setColis(colis);
			historiqueRepository.save(h);
			return colis;
		 }else {
			 throw new ColisException("Modification du colis interdit arpès ramassage ");
		 }
	}

	@Override
	public void deleteColis(String numCommande) throws ColisException {
		LOGGER.info("Suprression colis {}" , numCommande);
		Colis colis = findColisById(numCommande);
		 colis.setDateModification(new Date());
		if(colis.getStatut().equals(Constants.NOUVEAU_COLIS)  || colis.getStatut().equals(Constants.EN_ATTENTE_RAMASSAGE)   )  {
			colisRepository.delete(colis);
		 }else {
			 throw new ColisException("Suppresion du colis interdit arpès ramassage ");
		 }
		
	}

	@Override
	public List<Colis> affectationColisToLivreur(String emailLivreur, List<Colis> colis) throws LivreurException {
		
		Livreur livreur = livreurRepository.findById(emailLivreur).orElse(null);

		if(livreur==null) {
			throw new LivreurException("Livreur introuvable "+emailLivreur);
		}
		
		for(Colis coli:colis) {
			LOGGER.info("Affectation {} colis au livreur {}", coli.getNumCommande() , emailLivreur);
			coli.setLivreur(livreur);
			coli.setDateModification(new Date());
			coli.setStatut(Constants.EN_ATTENTE_LIVRAISON);
			
			Historique h =Historique.getHistorique("Affectation du colis au livreur: "+coli.getNumCommande(), coli.getStatut(), "cniTest");
			
			
			coli = colisRepository.save(coli);
			h.setColis(coli);
			coli.getHistoriques().add(h);
			historiqueRepository.save(h);
			
		}
		
			
		return colis;
		
	}

	@Override
	public Colis affectationColisToLivreur(String cniLivreur, Colis colis) throws LivreurException {
		
		LOGGER.info("Affectation colis {} au livreur {}", colis.getNumCommande() , cniLivreur);
		
		Livreur livreur = livreurRepository.findById(cniLivreur).orElse(null);

		if(livreur==null) {
			throw new LivreurException("Livreur introuvable "+cniLivreur);
		}else {
			colis.setLivreur(livreur);
			colis.setDateModification(new Date());
			colis.setStatut(Constants.EN_ATTENTE_LIVRAISON);
			Historique h =Historique.getHistorique("Affectation du colis au livreur: "+colis.getNumCommande(), colis.getStatut(), "cniTest");
			colis = colisRepository.save(colis);
			
			colis.getHistoriques().add(h);
			h.setColis(colis);
			historiqueRepository.save(h);
		}
		
		colisRepository.save(colis);
		
		return colis;
	}
	
	@Override
	public Colis affectationColisToRamasseur(String cniRamasseur, Colis colis) throws LivreurException {
		Livreur livreur = livreurRepository.findById(cniRamasseur).orElse(null);
		if(livreur==null) {
			throw new LivreurException("Ramasseur introuvable "+cniRamasseur);
		}else {
			colis.setLivreur(livreur);
			colis.setStatut(Constants.EN_ATTENTE_RAMASSAGE);
			Historique h =Historique.getHistorique("Affectation du colis au rammasseur: "+colis.getNumCommande(), colis.getStatut(), "cniTest");
			colis = colisRepository.save(colis);
			colis.getHistoriques().add(h);
		}
		colisRepository.save(colis);
		
		return colis;
	}

	@Override
	public String generateCodeEnvoie() {
		int numberColis = colisRepository.getCountColis();
		numberColis  = numberColis+10001;
		return "PWC1032MA"+numberColis;
	}

	@Override
	public Page<Colis> findAllColisByLivreur(String emailLivreur, int page, int size) {
		return colisRepository.getAllColisByLivreur(emailLivreur, PageRequest.of(page, size));
	}

	@Override
	public Page<Colis> findAllColisByClient(String emailClient, int page, int size) {
		return colisRepository.getAllColisByClient(emailClient, PageRequest.of(page, size));
	}

	@Override
	public Page<Colis> findAllColisByUtilisateurs(String emailUtilisateur, int page, int size) {
		return colisRepository.getAllColisByClient(emailUtilisateur, PageRequest.of(page, size));
	}
	@Override
	public int getCountColis() {
		return colisRepository.getCountColis();
	}
	@Override
	public int getCountColisByStatut(String statut) {
		return colisRepository.getCountColisByStatut(statut);
	}
	
	@Override
	public int getColisByStatutAndDate(String statut, Date dateDebut, Date dateFin) {
		return colisRepository.getColisByStatutAndDate(statut, dateDebut, dateFin);
	}

	@Override
	public List<Colis> desaffectationColisToLivreur(String cniLivreur, List<Colis> colis) throws LivreurException {
		for(Colis coli : colis) {
			coli.setLivreur(null);
			Historique h =Historique.getHistorique("Désaffectation du colis au livreur: "+cniLivreur, coli.getStatut(), "cniTest");
			coli = colisRepository.save(coli);
			
			coli.getHistoriques().add(h);
			coli = colisRepository.save(coli);
			h.setColis(coli);
			historiqueRepository.save(h);
		}
		return colis;
	}

	@Override
	public Colis desaffectationColisToLivreur(String cniLivreur, Colis colis) throws LivreurException {
		
		colis.setLivreur(null);
		Historique h =Historique.getHistorique("Désaffectation du livreur: "+cniLivreur, colis.getStatut(), "cniTest");
		colis = colisRepository.save(colis);
		
		colis.getHistoriques().add(h);
		colis = colisRepository.save(colis);
		h.setColis(colis);
		historiqueRepository.save(h);
		return colis;
	}
	
	@Override
	public Page<Colis> getAll(Pageable pageable) {
		return colisRepository.getAll(pageable);
	}
	@Override
	public Page<Colis> getAllColisByLivreur(String emailLivreur, Pageable pageable) {
		return colisRepository.getAllColisByLivreur(emailLivreur, pageable);
	}
	@Override
	public Page<Colis> getAllColisByClient(String emailClient, Pageable pageable) {
		return colisRepository.getAllColisByClient(emailClient, pageable);
	}
	@Override
	public Page<Colis> getAllColisUtilisateur(Pageable pageable) {
		return colisRepository.getAllColisUtilisateur(pageable);
	}
	@Override
	public List<Colis> getAllColisWithoutBonRamassage() {
		return colisRepository.getAllColisWithoutBonRamassage();
	}
	@Override
	public List<Colis> getAllColisWithoutBonLivraison() {
		return colisRepository.getAllColisWithoutBonLivraison();
	}

	@Override
	public List<Colis> getAllColisWithoutBonExpedition() {
		return colisRepository.getAllColisWithoutBonExpedition();
	}

	@Override
	public List<Colis> getAllColisWithBonRetour() {
		return colisRepository.getAllColisWithBonRetour();
	}

	@Override
	public Page<Colis> getAllColisByStatut(String statut, Pageable pageable) {
		return colisRepository.getAllColisByStatut(statut, pageable);
	}

	@Override
	public List<Historique> getHistoriqueColis(String numCommande) {
		
		return historiqueRepository.findHistoriqueColisByNom(numCommande);
		
		
	}

	@Override
	public Page<Historique> getHistoriqueColis(String numCommande, int page, int size) {
		
		return  historiqueRepository.findHistoriqueColisByNumCommande(numCommande, PageRequest.of(page, size));
	}

	@Override
	public Commentaire addCommentaireToColis(String numCommande, Commentaire commentaire) {
		Colis c= colisRepository.findById(numCommande).orElse(null);
		if(c!=null) {
			
			commentaire.setColis(c);
			commentaire=commentaireRepository.save(commentaire);
			return commentaire;
		}else {
			throw new IllegalArgumentException("colis introuvable"+numCommande);
		}
		
	}

	@Override
	public void deleteCommentaireToColis(long idCommentaire) {
		commentaireRepository.deleteById(idCommentaire);
	}

	@Override
	public List<Commentaire> getCommentairesColis(String numCommande) {
		return commentaireRepository.findCommentaireByColis(numCommande);
	}

	@Override
	public Page<Commentaire> getCommentairesColis(String numCommande, int page, int size) {
		return commentaireRepository.findCommentaireByColis2(numCommande,PageRequest.of(page, size));
	}
	

	
	

	
	

}
