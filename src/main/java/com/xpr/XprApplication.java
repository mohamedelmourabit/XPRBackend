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
import com.xpr.dao.CommentaireRepository;
import com.xpr.dao.FactureRepository;
import com.xpr.dao.HistoriqueRepository;
import com.xpr.dao.HistoriqueStockRepository;
import com.xpr.dao.LigneColisRepository;
import com.xpr.dao.LivreurRepository;
import com.xpr.dao.ProduitRepository;
import com.xpr.dao.ProfileRepository;
import com.xpr.dao.RamasseurRepository;
import com.xpr.dao.ServiceRepository;
import com.xpr.dao.StockRepository;
import com.xpr.dao.UtilisateurRepository;
import com.xpr.dao.VarianteRepository;
import com.xpr.dao.VarianteStockRepository;
import com.xpr.dao.VilleRepository;
import com.xpr.entities.Agence;
import com.xpr.entities.Autorisation;
import com.xpr.entities.BonExpedition;
import com.xpr.entities.BonLivraison;
import com.xpr.entities.BonRamassage;
import com.xpr.entities.BonRetour;
import com.xpr.entities.Business;
import com.xpr.entities.Client;
import com.xpr.entities.Colis;
import com.xpr.entities.Commentaire;
import com.xpr.entities.Facture;
import com.xpr.entities.Historique;
import com.xpr.entities.HistoriqueStock;
import com.xpr.entities.LigneColis;
import com.xpr.entities.Livreur;
import com.xpr.entities.Produit;
import com.xpr.entities.Profile;
import com.xpr.entities.Ramasseur;
import com.xpr.entities.Service;
import com.xpr.entities.Stock;
import com.xpr.entities.UtilisateurXpr;
import com.xpr.entities.Variante;
import com.xpr.entities.VarianteStock;
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
	private LigneColisRepository ligneColisRepository;
	
	@Autowired
	private VarianteRepository varianteRepository;
	
	@Autowired
	private VarianteStockRepository varianteStockRepository;
	
	@Autowired
	private AgenceRepository agenceRepository;

	@Autowired
	private BusinessRepository businessRepository;

	
	@Autowired
	private HistoriqueRepository historiqueRepository;
	
	@Autowired
	private CommentaireRepository commentaireRepository;
	
	@Autowired
	private HistoriqueStockRepository historiqueStockRepository;
	
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
		utilisateurXpr.setCni("cniUserXpr1");
		utilisateurXpr.setNom("userXpr1");
		utilisateurXpr.setPassword("123456");
		utilisateurXpr.setEmail("userXpr1@xpr.com");
		utilisateurXpr.setService(service);
		
		utilisateurXpr.getProfiles().add(profile1);
		utilisateurXpr.getAuthorities().add(autorisation7);
		utilisateurRepository.save(utilisateurXpr);
		utilisateurRepository.save(utilisateurXpr);
		

		Ville fes  = new Ville();
	
		fes.setNom("FES");
		fes = villeRepository.save(fes);
		
		Client client = new Client();
		
		client.setCni("cniClient");
		client.setNom("client1");
		client.setEmail("client1@contact.com");
		client.setIce("iceClient1");
		client.setPrefixCommande("Client1Prefix");
		client.setVille(fes);
		
		client = clientRepository.save(client);
		
		
		UtilisateurXpr utilisateurClientXpr2 = new UtilisateurXpr();
		utilisateurClientXpr2.getAuthorities().add(autorisation);
		utilisateurClientXpr2.getAuthorities().add(autorisation2);
		utilisateurClientXpr2.setCni("cniUserXpr2");
		utilisateurClientXpr2.setNom("userXpr2");
		utilisateurClientXpr2.setPassword("123456");
		utilisateurClientXpr2.setEmail("userXpr1@xpr.com");
		utilisateurClientXpr2.setClient(client);
		utilisateurRepository.save(utilisateurClientXpr2);
		
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
		colis1.getCommentaires().add(c);
		colis1.setClient(client1);
		colis1.setDestinataire("Houssam");
		colis1.getCommentaires().add(c);
		colis1.setDateLivraison(null);
		colis1.setDateRamassage(new Date());	
		colis1.setDestinataire("destinataire1");
		colis1.setTypeLivraison("A domicile");
		colis1.setPrix(1000.00);
		colis1.setProduit("produit1");
		
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
		commentaireRepository.save(c);
		historiqueRepository.save(h);
		
	
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
		h.setBonLivraison(bl);
		h.setUtilisateur(utilisateurClientXpr2);
		h.setStatut(bl.getStatut());
		h.setDateCreation(new Date());
		
		bl.getHistoriques().add(h);
		
		bonLivraisonRepository.save(bl);
		historiqueRepository.save(h);
		
		System.out.println("bl "+ bl.getNom());
		
		
		
		// Generation du Bon ramassage
		
		BonRamassage br=new BonRamassage();
		br.setClient(client1);
		br.setCreerPar(utilisateurXpr);
		br.setDateCreation(new Date());
		br.setStatut("En cours de ramassage");
		br.getColis().add(colis1);
		colis1.setStatut("En cours de ramassaage");
		colis1.setDateRamassage(new Date());
		h=new Historique();
		h.setAction("creer nouveau br");
		h.setBonRamassage(br);
		h.setUtilisateur(utilisateurClientXpr2);
		h.setStatut(br.getStatut());
		h.setDateCreation(new Date());
		
		br.setRamasseur(ramasseur1);
		br.setAgence(agenceFes);
		br.getHistoriques().add(h);
		
		br = bonRamassageRepository.save(br);
		System.out.println("br "+ br.getNom());
		historiqueRepository.save(h);
		
		
		// ramassage par le livreur depot sur agenceFES et envoi vers Casa
		
		br.setStatut("ramassé par le ramasseur");
		h=new Historique();
		h.setAction("réception colis agenceFes");
		h.setBonRamassage(br);
		h.setUtilisateur(utilisateurClientXpr2);
		h.setStatut(br.getStatut());
		h.setDateCreation(new Date());
		
		br.getHistoriques().add(h);
		br = bonRamassageRepository.save(br);
		historiqueRepository.save(h);
		colis1.setStatut("réception sur agenceFes");
		h=new Historique();
		h.setAction("réception colis sur agenceFes");
		h.setColis(colis1);
		h.setUtilisateur(utilisateurXpr);
		h.setStatut(br.getStatut());
		h.setDateCreation(new Date());
		
		colis1.getHistoriques().add(h);
		
		colisRepository.save(colis1);
		historiqueRepository.save(h);
		
		
		
		
		BonExpedition be=new BonExpedition();
		be.setCreerPar(utilisateurXpr);
		be.setDateCreation(new Date());
		be.setStatut(Constants.NOUVEAU_BE);
		be.getColis().add(colis1);
		colis1.setStatut("En cours d'expedition");
		h=new Historique();
		h.setAction("creer nouveau be");
		h.setBonExpedition(be);
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
		be.getHistoriques().add(h);
		
		be = bonExpeditionRepository.save(be);
		System.out.println("be "+ be.getNom());
		historiqueRepository.save(h);
		// arrive vers casa
		
		be.setStatut(Constants.EXPEDIE);
		h=new Historique();
		h.setAction("Confirmation reception expedition");
		h.setBonExpedition(be);
		h.setUtilisateur(utilisateurXpr);
		h.setStatut(br.getStatut());
		h.setDateCreation(new Date());
		be.getHistoriques().add(h);
		historiqueRepository.save(h);
		colis1.setStatut(Constants.EXPEDIE);
		
		h=new Historique();
		h.setAction("expedié");
		h.setColis(colis1);
		h.setUtilisateur(utilisateurXpr);
		h.setStatut(br.getStatut());
		h.setDateCreation(new Date());
		colis1.getHistoriques().add(h);
		
		be = bonExpeditionRepository.save(be);
		historiqueRepository.save(h);
		
		
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
		historiqueRepository.save(h);
		
		
		// Livraison client
		
		colis1.setLivreur(livreur2);
		colis1.setStatut(Constants.LIVRE);
		colis1.setDateLivraison(new Date());
		colisRepository.save(colis1);
		h=new Historique();
		h.setAction("Livré au client");
		h.setColis(colis1);
		h.setUtilisateur(livreur2);
		h.setStatut(br.getStatut());
		h.setDateCreation(new Date());
		colis1.getHistoriques().add(h);
		historiqueRepository.save(h);
		
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
		
		System.out.println("facture " + facture.getName());
		
		// apres reception du paiement par le livreur
		
		
		facture.setStatut("Reglé");
		factureRepository.save(facture);
		
		
		// case de figure avec Stock
		
		Produit produit = new Produit();
		produit.setClient(client1);
		produit.setContainVariantes(true);
		produit.setDimension("10 / 10");
		produit.setEmballer(true);
		produit.setIdIntern("produit1");
		produit.setMarque("marque1");
		produit.setNom("nomProduit1");
		produit.setNature("nature1");
		
		Variante variante1= new Variante();
		variante1.setPrix(1500);
		variante1.setSku("SKU1");
		variante1.setProduit(produit);
		variante1.setQte(100);
		variante1.setQteReserve(0);
		
		Variante variante2= new Variante();
		variante2.setPrix(2500);
		variante2.setSku("SKU");
		variante2.setProduit(produit);
		variante2.setQte(500);
		variante2.setQteReserve(0);
		
		
		
		produit = produitRepository.save(produit);
		variante1.setProduit(produit);
		variante2.setProduit(produit);
		variante1 = varianteRepository.save(variante1);
		variante2 = varianteRepository.save(variante2);
		
		
		// avant creation stock est ce qu'il y'a un bon livraison et  bon rammassage ?
		
		// Creation stock agence avec variante1
		
		Stock s = new Stock();
		s.setAgence(agenceFes);
		s.setClient(client1);
		s.setVille(fes);
		s.setVariante(variante1);
		s.setQte(50);
		s.setVariante(variante1);
		s.setQteNonLivre(50);
		s.setQteLivre(0);
		s.setQteEnCoursLivraison(0);
		
		
		s = stockRepository.save(s);
		
		HistoriqueStock hs=new HistoriqueStock();
		hs.setCreationDate(new Date());
		hs.setQte(s.getQte());
		hs.setUtilisateur(utilisateurXpr);
		hs.setStock(s);
		hs.setAction("Creation du stock sur agence variante: "+variante1.getSku()+"agence="+agenceFes.getNom());
		historiqueStockRepository.save(hs);
		
		
		VarianteStock vs= new VarianteStock();
		vs.setStock(s);
		vs.setVariante(variante1);
		vs.setQte(s.getQte());
		varianteStockRepository.save(vs);
		
		
		// Creation stock agence avec variante2
		
				Stock s2 = new Stock();
				s2.setAgence(agenceFes);
				s2.setClient(client1);
				s2.setVille(fes);
				s2.setVariante(variante2);
				s2.setQte(500);
				s2.setVariante(variante1);
				s2.setQteNonLivre(500);
				s2.setQteLivre(0);
				
			
				s2 = stockRepository.save(s2);
				VarianteStock vs2= new VarianteStock();
				vs2.setStock(s2);
				vs2.setVariante(variante2);
				vs2.setQte(s2.getQte());
				varianteStockRepository.save(vs2);
		
		
		// Creation stock livreur 20 variante1 
		
		int qteVarianteReserveStock= varianteStockRepository.getSumQteStockVariante(variante1.getSku());
		
		int stockAvailable = variante1.getQte()-qteVarianteReserveStock;
		
		if(stockAvailable>=20) {
		
			Stock stockLivreur = new Stock();
			stockLivreur.setLivreur(livreur1);
			stockLivreur.setVariante(variante1);
			stockLivreur.setClient(client1);
			stockLivreur.setVille(fes);
			stockLivreur.setVariante(variante1);
			stockLivreur.setQte(20);
			stockLivreur.setVariante(variante1);
			stockLivreur.setQteNonLivre(20);
			stockLivreur.setQteLivre(0);
			
			stockLivreur =stockRepository.save(stockLivreur);
			 hs=new HistoriqueStock();
			 hs.setCreationDate(new Date());
			 hs.setQte(s.getQte());
			 hs.setUtilisateur(utilisateurXpr);
			 hs.setStock(s);
			 hs.setAction("Creation du stock sur livreur variante: "+variante1.getSku()+"livreur="+livreur1.getCni());
			 historiqueStockRepository.save(hs);
			
			VarianteStock vs1= new VarianteStock();
			vs1.setStock(stockLivreur);
			vs1.setVariante(variante1);
			vs1.setQte(stockLivreur.getQte());
			varianteStockRepository.save(vs);
		}
		
		// creation colis avec 2: variante1 et 1 variante2 à partir du stock sur agence fes
		Colis colisStock = new Colis();
		colisStock.setClient(client1);
		colisStock.setAdresse("adrees xxx");
		colisStock.setCreerPar(utilisateurXpr);
		colisStock.setDateCreation(new Date());
		colisStock.setApplicationFrais(true);
		colisStock.setTypeLivraison("type livraison1");
		colisStock.setStatut(Constants.EN_ATTENTE_LIVRAISON);
		colisStock.setVilleDestination(fes); // livré sur meme ville de stock donc à priori c'est sans bon expedition ?
		
		 numberColis = colisRepository.getCountColis();
		//numberColis  = numberColis+10001;//annnée
		
		colisStock.setCodeEnvoi("PWC1032MA"+numberColis);
		
		
		colisStock = colisRepository.save(colisStock);
		
			
		LigneColis lc1=new LigneColis();
		lc1.setColis(colisStock);
		lc1.setVariante(variante1);
		lc1.setStock(s);
		lc1.setQte(2);
		
		
		LigneColis lc2=new LigneColis();
		lc2.setColis(colisStock);
		lc2.setVariante(variante2);
		lc2.setStock(s2);
		lc2.setQte(1);
		
		colisStock.getLigneColis().add(lc1);
		colisStock.getLigneColis().add(lc2);
		
		s.setQteEnCoursLivraison(s.getQteEnCoursLivraison()+lc1.getQte());
		s.setQteNonLivre(s.getQteNonLivre()+lc1.getQte());
		
		
		
		s =stockRepository.save(s);
		
		
		s2.setQteEnCoursLivraison(s2.getQteEnCoursLivraison()+lc2.getQte());
		s2.setQteNonLivre(s2.getQteNonLivre()+lc2.getQte());
		s2 =stockRepository.save(s2);
		
		
		// Pour cette exemple de stock sur fes et livraison pas de bon expediton
		
		
		
		//affectation au livreur  
		
		colisStock.setLivreur(livreur1);
		colisStock.setStatut(Constants.EN_ATTENTE_LIVRAISON);
		h=new Historique();
		h.setAction("Affectation au livreur");
		h.setColis(colisStock);
		h.setUtilisateur(utilisateurXpr);
		h.setStatut(colisStock.getStatut());
		h.setDateCreation(new Date());
		colisStock.getHistoriques().add(h);
	
		historiqueRepository.save(h);
		
		//
		
		// changement de setQteEnCoursLivraison stock
		
		for(LigneColis l : colisStock.getLigneColis()) {
			
		 Stock stockLigneColis=	l.getStock();
		 
		 stockLigneColis.setQteEnCoursLivraison( stockLigneColis.getQteEnCoursLivraison() +l.getQte());
		 
		 stockRepository.save(stockLigneColis);
		 
		}
		
				
		// Livraison au client final
					
		
		colisStock.setStatut(Constants.LIVRE);
		colisStock.setDateLivraison(new Date());
		colisRepository.save(colisStock);
		
		h=new Historique();
		h.setAction("Livré au client");
		h.setColis(colis1);
		h.setUtilisateur(livreur1);
		h.setStatut(br.getStatut());
		h.setDateCreation(new Date());
		colisStock.getHistoriques().add(h);
		historiqueRepository.save(h);
		
		// case exceptionnel lors de la livraion le client finale ne veut qu'une 1 variante1 et 1variante2
		
		// selection des variantes non livré
		
		Set<LigneColis> ligneColisLivre=new HashSet<LigneColis>();
		Set<LigneColis> ligneColisNonLivre=new HashSet<LigneColis>();
		for(LigneColis l : colisStock.getLigneColis()) { 
			l.setId(null);
			if(l.getVariante().getSku().equals("variante1")) {
				l.setQte(1);
				ligneColisNonLivre.add(l);
				
				l.setQte(1);
				ligneColisLivre.add(l);
				
			}else {
				ligneColisLivre.add(l);
			}
			
		}
		ligneColisRepository.saveAll(ligneColisNonLivre);
		ligneColisRepository.saveAll(ligneColisLivre);
		
		
		// Mise à jour  qute stock changement des quantités livré sur stock extrait depuis ligneColis
		
		for(LigneColis l : colisStock.getLigneColisLivre()) {  
					
			Stock stockLigneColis=	l.getStock();
				 
			stockLigneColis.setQteEnCoursLivraison(stockLigneColis.getQteEnCoursLivraison()-l.getQte());
			stockLigneColis.setQteLivre(stockLigneColis.getQteLivre()+l.getQte());
			stockLigneColis.setQteNonLivre(stockLigneColis.getQteNonLivre()-l.getQte());
		

		    stockRepository.save(stockLigneColis);
				 
		}
		
		
		
		// de preference création du bon retour de 1:variante1 
		
		BonRetour bonRetour = new BonRetour();
		
		bonRetour.setClient(client1);
		bonRetour.setLivreur(livreur1);
		bonRetour.setDateCreation(new Date());
		// historque creation bon retour par le livreur
		bonRetour.setCreerPar(livreur1);
		bonRetour.getLigneColisRetourne().addAll(colisStock.getLigneColisRetourne());
		bonRetour.setStatut("En attente de retour au stock");
		bonRetour= bonRetourRepository.save(bonRetour);
		
		// retourne au stock 
		
		for(LigneColis l : bonRetour.getLigneColisRetourne()) {  
			
			Stock stockLigneColis=	l.getStock();
				 
			stockLigneColis.setQteEnCoursLivraison(stockLigneColis.getQteEnCoursLivraison()-l.getQte());
			
			stockLigneColis.setQteNonLivre(stockLigneColis.getQteNonLivre()+l.getQte());
		

		    stockRepository.save(stockLigneColis);
				 
		}
		
		// historque creation bon retour par le livreur où receptioniste agence
		bonRetour.setStatut("Retourné au stock");
		bonRetour.setDateModification(new Date());
		bonRetour= bonRetourRepository.save(bonRetour);
		
		
		// facturation par le  livreur1
		
		facture = new Facture();
		facture.getColis().add(colisStock);
		facture.setClient(client1);
		facture.setLivreur(livreur1);
		facture.setCreerPar(livreur1);
		facture.setStatut("Non reglé");
		
		//int nbColis = 0;
					
		for(Colis cc : facture.getColis()) {
			montantTotalNet=0.0;
					
			for(LigneColis lc:cc.getLigneColis()) {
				montantTotalNet = montantTotalNet + lc.getVariante().getPrix();
				//nbColis = nbColis+lc.getQteLivre();
			}
						
		}
				
		facture.setNbrColis(facture.getColis().size());
		facture.setTotalNet(montantTotalNet);
				
		if(facture.getColis()!=null) {
			facture.setNbrColis(facture.getColis().size());
		}
				
		facture.setType("Livré");
		facture = factureRepository.save(facture);
				
		System.out.println("facture " + facture.getName());
				
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
		
		// si il a dejà un stock en cours d'éxpedition
		
		
		
		
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
		
		
	}

}
