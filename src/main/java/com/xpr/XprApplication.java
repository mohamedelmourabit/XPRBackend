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

import com.xpr.dao.AgenceRepository;
import com.xpr.dao.AutorisationRepository;
import com.xpr.dao.BonExpeditionRepository;
import com.xpr.dao.BonRamassageRepository;
import com.xpr.dao.BonRetourRepository;
import com.xpr.dao.BusinessRepository;
import com.xpr.dao.ClientRepository;
import com.xpr.dao.ColisRepository;
import com.xpr.dao.FactureRepository;
import com.xpr.dao.LivreurRepository;
import com.xpr.dao.ProduitRepository;
import com.xpr.dao.ProfileRepository;
import com.xpr.dao.RamasseurRepository;
import com.xpr.dao.ServiceRepository;
import com.xpr.dao.StockRepository;
import com.xpr.dao.UtilisateurRepository;
import com.xpr.dao.VarianteRepository;
import com.xpr.dao.VilleRepository;
import com.xpr.entities.Agence;
import com.xpr.entities.Autorisation;
import com.xpr.entities.BonExpedition;
import com.xpr.entities.BonLivraison;
import com.xpr.entities.BonRamassage;
import com.xpr.entities.Business;
import com.xpr.entities.Client;
import com.xpr.entities.Colis;
import com.xpr.entities.Commentaire;
import com.xpr.entities.Facture;
import com.xpr.entities.Historique;
import com.xpr.entities.Livreur;
import com.xpr.entities.Profile;
import com.xpr.entities.Ramasseur;
import com.xpr.entities.Service;
import com.xpr.entities.UtilisateurXpr;
import com.xpr.entities.Ville;
import com.xpr.utils.Constants;

@SpringBootApplication
public class XprApplication implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(XprApplication.class);
	
	@Autowired
	private FactureRepository factureRepository;
	
	@Autowired
	private LivreurRepository livreurRepository;
	
	@Autowired
	private RamasseurRepository ramasseurRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ColisRepository colisRepository;
	
	@Autowired
	private com.xpr.dao.BonLivraisonRepository bonLivraisonRepository;
	
	@Autowired
	private VilleRepository villeRepository;
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private AutorisationRepository autorisationRepository;
	
	@Autowired
	private BonRetourRepository bonRetourRepository;
	
	@Autowired
	private BonExpeditionRepository bonExpeditionRepository;
	
	@Autowired
	private BonRamassageRepository bonRamassageRepository;
	
	@Autowired
	private ProduitRepository produitRepository;
	
	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private VarianteRepository varianteRepository;
	
	@Autowired
	private AgenceRepository agenceRepository;

	@Autowired
	private BusinessRepository businessRepository;

	public static void main(String[] args) {
		SpringApplication.run(XprApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Service service = new Service();
		service.setNom("service1");
		service = serviceRepository.save(service);
		
		
		Autorisation autorisation = new Autorisation();
		autorisation.setAuthName("lecture_colis");
		
		Autorisation autorisation2 = new Autorisation();
		autorisation2.setAuthName("modification_colis");
		
		Autorisation autorisation3 = new Autorisation();
		autorisation3.setAuthName("lecture_bl");
		
		Autorisation autorisation4 = new Autorisation();
		autorisation4.setAuthName("modification_bl");
		
		Autorisation autorisation5 = new Autorisation();
		autorisation5.setAuthName("lecture_br");
		
		Autorisation autorisation6 = new Autorisation();
		autorisation6.setAuthName("modification_br");
		
		Autorisation autorisation7 = new Autorisation();
		autorisation7.setAuthName("suppression_colis");
		
		autorisationRepository.save(autorisation);autorisationRepository.save(autorisation2);
		autorisationRepository.save(autorisation3);autorisationRepository.save(autorisation4);autorisationRepository.save(autorisation5);autorisationRepository.save(autorisation6);
		autorisationRepository.save(autorisation7);
		
		Profile profile1=new Profile();
		profile1.setPrflName("Manager");
		
		profile1.getAuthorities().add(autorisation);profile1.getAuthorities().add(autorisation2);
		profile1.getAuthorities().add(autorisation3);profile1.getAuthorities().add(autorisation4);profile1.getAuthorities().add(autorisation5);profile1.getAuthorities().add(autorisation6);
		profileRepository.save(profile1);
		
		Profile profile2=new Profile();
		profile2.setPrflName("Livreur");
		
		profile2.getAuthorities().add(autorisation);profile2.getAuthorities().add(autorisation2);
		profileRepository.save(profile2);
		
		Profile profile3=new Profile();
		profile3.getAuthorities().add(autorisation5);profile3.getAuthorities().add(autorisation6);
		profileRepository.save(profile3);
		
		profile3 = profileRepository.save(profile3);
		
		
		UtilisateurXpr utilisateurXpr = new UtilisateurXpr();
		utilisateurXpr.getAuthorities().add(autorisation);utilisateurXpr.getAuthorities().add(autorisation2);
		utilisateurXpr.setCni("cniUserXpr1");
		utilisateurXpr.setNom("userXpr1");
		utilisateurXpr.setPassword("123456");
		utilisateurXpr.setEmail("userXpr1@xpr.com");
		utilisateurXpr.setService(service);
		
		utilisateurXpr.getProfiles().add(profile1);
		utilisateurXpr.getAuthorities().add(autorisation7);
		utilisateurRepository.save(utilisateurXpr);
		
		
		
		utilisateurXpr.getProfiles().add(profile1);
		utilisateurXpr.getAuthorities().add(autorisation7);
		utilisateurRepository.save(utilisateurXpr);
		
		
		Client client = new Client();
		
		client.setCni("cniClient");
		client.setNom("client1");
		client.setEmail("client1@contact.com");
		client.setIce("iceClient1");
		client.setPrefixCommande("Client1Prefix");
		
		client = clientRepository.save(client);
		
		
		UtilisateurXpr utilisateurClientXpr2 = new UtilisateurXpr();
		utilisateurClientXpr2.getAuthorities().add(autorisation);utilisateurXpr.getAuthorities().add(autorisation2);
		utilisateurClientXpr2.setCni("cniUserXpr1");
		utilisateurClientXpr2.setNom("userXpr1");
		utilisateurClientXpr2.setPassword("123456");
		utilisateurClientXpr2.setEmail("userXpr1@xpr.com");
		utilisateurClientXpr2.setClient(client);
		utilisateurRepository.save(utilisateurClientXpr2);
		
		
		Ville fes  = new Ville();
		fes.setNom("FES");
		villeRepository.save(fes);
		
		
		Agence agenceFes=new Agence();
		agenceFes.setNom("Agence fes");
		agenceFes.setVille(fes);
		agenceRepository.save(agenceFes);
		
		Ville casa  = new Ville();
		casa.setNom("Casa");
		villeRepository.save(casa);
		
		Agence agenceCasa=new Agence();
		agenceCasa.setNom("Agence Casa");
		agenceCasa.setVille(casa);
		agenceRepository.save(agenceCasa);
		
		
		Ramasseur ramasseur1 = new Livreur();
		ramasseur1.getProfiles().add(profile3);
		ramasseur1.setCni("CNIramasseur1");
		ramasseur1.setEmail("ramasseur1@xpr.com");
		ramasseur1.setPassword("123456");
		ramasseur1.setPrenom("prenomramasseur1");
		ramasseur1.setNom("nomramasseur1");
		LOGGER.debug("Ajout d'un nouveau ramasseur : ramasseur1");
		ramasseurRepository.save(ramasseur1);
		
		
		Livreur livreur1 = new Livreur();
		livreur1.getProfiles().add(profile2);
		livreur1.setCni("CNILivreur1");
		livreur1.setEmail("livreur1@xpr.com");
		livreur1.setPassword("123456");
		livreur1.setPrenom("prenomlivreur1");
		livreur1.setNom("nomlivreur1");
		livreur1.getVilles().add(fes);
		LOGGER.debug("Ajout d'un nouveau livreur : livreur1");
		livreurRepository.save(livreur1);
		
		Livreur livreur2 = new Livreur();
		livreur2.setCni("CNILivreur2");
		livreur2.getProfiles().add(profile2);
		livreur2.setPrenom("prenomlivreur2");
		livreur2.setPassword("123456");
		livreur2.setNom("nomlivreur2");
		livreur2.setEmail("livreur2@xpr.com");
		livreur1.getVilles().add(casa);
		LOGGER.debug("Ajout d'un nouveau livreur : livreur2");
		livreurRepository.save(livreur2);
		
		Client client1 = new Client();
		client1.setCni("CNICLIENT");
		client1.setContact("client1Contact");
		client1.setPrefixCommande("PR");
		client1.setVille(fes);
		LOGGER.debug("add Client1");
		client1 =clientRepository.save(client1);
		
		Business business = new Business();
		business.setNom("business1");
		business.setClient(client1);
		
		businessRepository.save(business);
				
		
		
		Colis colis1= new Colis();
		colis1.setCreerPar(utilisateurXpr);
		colis1.setDateCreation(new Date());
		
		int numberColis = colisRepository.getCountColis();
		//numberColis  = numberColis+10001;//annnée
		
		colis1.setCodeEnvoi("PWC1032MA"+numberColis);
		
		colis1.setClient(client1);
		colis1.setAdresse("addresse1");
		colis1.setApplicationFrais(true);
		colis1.setApplicationFraisAssurance(false);
		Commentaire c = new Commentaire();
		c.setColis(colis1);
		c.setDateCreation(new Date());
		c.setCommentaire("merci de le livrer au plus vite");
		c.setUtilisateur(utilisateurXpr);
		colis1.setClient(client1);
		colis1.setDestinataire("Houssam");
		colis1.getCommentaires().add(c);
		colis1.setDateLivraison(null);
		colis1.setDateRamassage(new Date());	
		colis1.setDestinataire("destinataire1");
		colis1.setTypeLivraison("A domicile");
		colis1.setPrix(1000.00);
		
		Historique h=new Historique();
		h.setAction("creer nouveau colis");
		h.setColis(colis1);
		h.setUtilisateur(utilisateurXpr);
		h.setStatut(colis1.getStatut());
		h.setDateCreation(new Date());
		colis1.getHistoriques().add(h);
		colis1.setVilleDestination(casa);
		colis1.setStatut(Constants.EN_ATTENTE_RAMASSAGE);
		colisRepository.save(colis1);
		
		
		
	
		// affectation au ramasseur si y'a pas de stock 
		
		colis1.setRamasseur(ramasseur1);
		colisRepository.save(colis1);
		
		// creation du Bl cote client
		
		BonLivraison bl=new BonLivraison();
		bl.setClient(client1);
		bl.setCreerPar(utilisateurClientXpr2);
		bl.setDateCreation(new Date());
		bl.setStatut("En cours de ramassage");
		bl.getColis().add(colis1);
		h=new Historique();
		h.setAction("creation nouveau bl");
		h.setColis(colis1);
		h.setUtilisateur(utilisateurClientXpr2);
		h.setStatut(bl.getStatut());
		h.setDateCreation(new Date());
		bl.getHistoriques().add(h);
		
		bonLivraisonRepository.save(bl);
		
		
		// Generation du Bon ramassage
		
		BonRamassage br=new BonRamassage();
		br.setClient(client1);
		br.setCreerPar(utilisateurXpr);
		br.setDateCreation(new Date());
		br.setStatut("En cours de ramassage");
		br.getColis().add(colis1);
		colis1.setStatut(Constants.EN_ATTENTE_RAMASSAGE);
		h=new Historique();
		h.setAction("creer nouveau br");
		h.setColis(colis1);
		h.setUtilisateur(utilisateurClientXpr2);
		h.setStatut(br.getStatut());
		h.setDateCreation(new Date());
		br.setRamasseur(ramasseur1);
		br.setAgence(agenceFes);
		br.getHistoriques().add(h);
		
		br = bonRamassageRepository.save(br);
		
		
		
		// ramassage par le livreur depot sur agenceFES et envoi vers Casa
		
		br.setStatut("ramassé par le ramasseur");
		h=new Historique();
		h.setAction("réception colis agenceFes");
		h.setColis(colis1);
		h.setUtilisateur(utilisateurClientXpr2);
		h.setStatut(br.getStatut());
		h.setDateCreation(new Date());
		br.getHistoriques().add(h);
		br = bonRamassageRepository.save(br);
		colis1.setStatut("réception sur agenceFes");
		h=new Historique();
		h.setAction("réception colis sur agenceFes");
		h.setColis(colis1);
		h.setUtilisateur(utilisateurXpr);
		h.setStatut(br.getStatut());
		h.setDateCreation(new Date());
		colis1.getHistoriques().add(h);
		colisRepository.save(colis1);
		
		
		BonExpedition be=new BonExpedition();
		be.setCreerPar(utilisateurXpr);
		be.setDateCreation(new Date());
		be.setStatut(Constants.NOUVEAU_BE);
		be.getColis().add(colis1);
		colis1.setStatut("En cours d'expedition");
		h=new Historique();
		h.setAction("creer nouveau be");
		h.setColis(colis1);
		h.setUtilisateur(utilisateurXpr);
		h.setStatut(br.getStatut());
		h.setDateCreation(new Date());
		
		
		be.setDepart(agenceFes);
		be.setDestination(agenceCasa);
		be.setLogistique("CTM");
		be.setRefBonLogistique("reference1");
		be.getHistoriques().add(h);
		
		h=new Historique();
		h.setAction("en cours expedition");
		h.setColis(colis1);
		h.setUtilisateur(utilisateurXpr);
		h.setStatut(br.getStatut());
		h.setDateCreation(new Date());
		colis1.getHistoriques().add(h);
		
		be = bonExpeditionRepository.save(be);
			
		
		// arrive vers casa
		
		be.setStatut(Constants.EXPEDIE);
		h=new Historique();
		h.setAction("Confirmation reception expedition");
		h.setColis(colis1);
		h.setUtilisateur(utilisateurXpr);
		h.setStatut(br.getStatut());
		h.setDateCreation(new Date());
		be.getHistoriques().add(h);
		colis1.setStatut(Constants.EXPEDIE);
		
		h=new Historique();
		h.setAction("expedié");
		h.setColis(colis1);
		h.setUtilisateur(utilisateurXpr);
		h.setStatut(br.getStatut());
		h.setDateCreation(new Date());
		colis1.getHistoriques().add(h);
		
		be = bonExpeditionRepository.save(be);
		
		//affectation au livreur  
		
		colis1.setLivreur(livreur2);
		colis1.setStatut(Constants.EN_ATTENTE_LIVRAISON);
		h=new Historique();
		h.setAction("Affectation au livreur");
		h.setColis(colis1);
		h.setUtilisateur(utilisateurXpr);
		h.setStatut(br.getStatut());
		h.setDateCreation(new Date());
		colis1.getHistoriques().add(h);
		
		// Livraison client
		
		colis1.setLivreur(livreur2);
		colis1.setStatut(Constants.LIVRE);
		h=new Historique();
		h.setAction("Livré au client");
		h.setColis(colis1);
		h.setUtilisateur(livreur2);
		h.setStatut(br.getStatut());
		h.setDateCreation(new Date());
		colis1.getHistoriques().add(h);
		
		// facturation par le  livreur 2
		
		Facture facture = new Facture();
		facture.getColis().add(colis1);
		facture.setClient(client1);
		facture.setLivreur(livreur2);
		facture.setCreerPar(livreur2);
		facture.setStatut("Non reglé");
		facture.setNbrColis(facture.getColis().size());
		
		double montantTotalNet=0.0;
		
		
		for(Colis c1 : facture.getColis()) {
			montantTotalNet = montantTotalNet + c1.getPrix();
		}
		facture.setTotalNet(montantTotalNet);
		
		if(facture.getColis()!=null) {
			facture.setNbrColis(facture.getColis().size());
		}
		
		facture.setType("Livré");
		facture = factureRepository.save(facture);
		
		// apres reception du paiement par le livreur
		
		
		facture.setStatut("Reglé");
		factureRepository.save(facture);
		
		
		
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
		
		
		
		
		// Apres Livraison
		colis1.setStatut("Livré");
		colisRepository.save(colis1);
		
	}

}
