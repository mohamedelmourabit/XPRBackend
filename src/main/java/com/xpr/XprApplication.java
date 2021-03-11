package com.xpr;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.xpr.dao.ClientRepository;
import com.xpr.dao.ColisRepository;
import com.xpr.dao.FactureRepository;
import com.xpr.dao.LivreurRepository;
import com.xpr.dao.VilleRepository;
import com.xpr.entities.BonLivraison;
import com.xpr.entities.Business;
import com.xpr.entities.Client;
import com.xpr.entities.Colis;
import com.xpr.entities.Commentaire;
import com.xpr.entities.Facture;
import com.xpr.entities.Livreur;
import com.xpr.entities.UtilisateurXpr;
import com.xpr.entities.Ville;

@SpringBootApplication
public class XprApplication implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(XprApplication.class);
	
	@Autowired
	private FactureRepository factureRepository;
	
	@Autowired
	private LivreurRepository livreurRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ColisRepository colisRepository;
	
	@Autowired
	private com.xpr.dao.BonLivraisonRepository bonLivraisonRepository;
	
	@Autowired
	private VilleRepository villeRepository;

	public static void main(String[] args) {
		SpringApplication.run(XprApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		Ville fes  = new Ville();
		fes.setNom("FES");
		villeRepository.save(fes);
		
		Livreur livreur1 = new Livreur();
		livreur1.setCni("CNILivreur1");
		livreur1.setEmail("livreur1@xpr.com");
		livreur1.setPassword("123456");
		livreur1.setPrenom("prenomlivreur1");
		livreur1.setNom("nomlivreur1");
		LOGGER.debug("Ajout d'un nouveau livreur : livreur1");
		livreurRepository.save(livreur1);
		
		Livreur livreur2 = new Livreur();
		livreur2.setCni("CNILivreur2");
		livreur2.setPrenom("prenomlivreur2");
		livreur2.setPassword("123456");
		livreur2.setNom("nomlivreur2");
		livreur2.setEmail("livreur2@xpr.com");
		LOGGER.debug("Ajout d'un nouveau livreur : livreur2");
		livreurRepository.save(livreur2);
		
		Client client1 = new Client();
		client1.setCni("CNICLIENT");
		client1.setContact("client1Contact");
		client1.setPrefixCommande("PR");
		LOGGER.debug("add Client1");
		client1 =clientRepository.save(client1);
		
		Business business = new Business();
		business.setNom("business1");
		business.setClient(client1);
		
		client1.getBussiness().add(business);
		
		
		
		UtilisateurXpr utilisateurXpr1 = new UtilisateurXpr();
		utilisateurXpr1.setCni("CD10");
		utilisateurXpr1.setNom("utilisateurClient1");
		
		
		
		Colis colis1= new Colis();
		colis1.setNumCommande("10000");
		
		int numberColis = colisRepository.getCountColis();
		numberColis  = numberColis+10001;//annnée
		
		colis1.setCodeEnvoi("PWC1032MA"+numberColis);
		
		colis1.setClient(client1);
		colis1.setAdresse("addresse1");
		colis1.setApplicationFrais(true);
		colis1.setApplicationFraisAssurance(false);
		Commentaire c = new Commentaire();
		c.setColis(colis1);
		c.setDateCreation(new Date());
		c.setUtilisateur(utilisateurXpr1);
		c.setCommentaire("merci de le livrer au plus vite");
		
		colis1.getCommentaires().add(c);
		colis1.setDateLivraison(new Date());
		colis1.setDateRamassage(new Date());	
		colis1.setDestinataire("destinataire1");
		colis1.setTypeLivraison("A domicile");
		colis1.setPrix(1000.00);
		
		colis1.setVilleDestination(fes);
		colis1.setStatut("En attente de ramassage");
		colisRepository.save(colis1);
		
		
		// affectation au ramasseur si y'a pas de stock 
		
		// scan commande
		
		//affectation au livreur  
		
		// ajouter du ticket apres chaque nouveau colis
		
		
		// stock déjà chez le livreur  
		
		// Ramasseur ramasser où pas 
		
		// client saisie BL 3ndna f system genere un QR code
		
		//  rammseur scan code pour rentrer les produits dans notre agence pour dispatcher
		
		
		// lecture_  modification_   suppression_ chez superadmin uniquement  
		
		
		// apres rammassage pas de modification du colis ni bu BL 
		
		// action ramassage 
		
		
		//Avant ramassage
		colis1.setLivreur(livreur1);
		// si il a dejà un stock en cours d'éxpedition
		colis1.setStatut("En attente de ramassage");
		colisRepository.save(colis1);
		
		
		//Génération BL en Ramassage
		colis1.setStatut("En cours de livraison");
		colisRepository.save(colis1);
		BonLivraison bonLivraison = new BonLivraison();
		bonLivraison.setClient(client1);
		bonLivraison.setRamasseur(livreur1);
		
		Set<Colis > coliss = new HashSet<Colis>();
		coliss.add(colis1);
		
		bonLivraison.setColis(coliss);
		
		bonLivraisonRepository.save(bonLivraison);
		
		// dispatching 
		
		//  agence receptioniste
		
		// reception en agence en cours de dispatch 
		
		// ==> dispatch des colis en zone  apres reception des produits physiquement en rayon
		
		// génération des listes des expeditions
		
		// Bon d'éxpedition qui s'alimente avec les colis recu auprès des ramasseurs QR code
		
		
		// deux cas de figures : 
		    // Affectation au livreur
		
		// Réception du Bon d'éxpedition 
		
		
		// Pour les clients vue colis uniquement pour suivre le statut des colis
		
		
		// Changement de statut de commande 
		
		// selection des colis livrée pour généré factures
		
		
		// Changemnets des status de la facture vers reglé par le livreur apres reception de paiement
		
		
		// Génération Facture et ticket
		
		Facture facture = new Facture();
		facture.setColis(coliss);
		facture.setClient(client1);
		facture.setStatut("Non reglé");
		
		double montantTotalNet=0.0;
		
		
		for(Colis c1 : facture.getColis()) {
			montantTotalNet = montantTotalNet + c1.getPrix();
		}
		facture.setTotalNet(montantTotalNet);
		
		if(facture.getColis()!=null) {
			facture.setNbrColis(facture.getColis().size());
		}
		
		facture.setType("Livré");
		factureRepository.save(facture);
		
		
		// Apres Livraison
		colis1.setStatut("Livré");
		colisRepository.save(colis1);
		
	}

}
