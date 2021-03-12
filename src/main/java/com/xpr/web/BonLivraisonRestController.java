package com.xpr.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpr.entities.BonLivraison;
import com.xpr.entities.Colis;
import com.xpr.entities.Historique;
import com.xpr.exceptions.BonLivraisonException;
import com.xpr.services.BonLivraisonService;

@RestController
public class BonLivraisonRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BonLivraisonRestController.class);
	
	@Autowired
	private BonLivraisonService bonLivraisonService;
	
	@RequestMapping(value="/getHistoriqueBonLivraison/{nom}",method=RequestMethod.GET)
	public List<Historique> getHistoriqueBonLivraison(@PathVariable String nom) {
		return bonLivraisonService.getHistoriqueBonLivraison(nom);
	}
	@RequestMapping(value="/getHistoriqueBonLivraisonPagination/{nom}",method=RequestMethod.GET)
	public Page<Historique> getHistoriqueBonLivraison(@PathVariable String nom, @RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return bonLivraisonService.getHistoriqueBonLivraison(nom, page, size);
	}
	@RequestMapping(value="/addColisToBonLivraison/{blId}",method=RequestMethod.PUT)
	public BonLivraison addColisToBonLivraison(@PathVariable String blId,@RequestBody  List<Colis> colis) throws BonLivraisonException {
		return bonLivraisonService.addColisToBonLivraison(blId, colis);
	}
	@RequestMapping(value="/deleteColisFomBonLivraison/{blId}",method=RequestMethod.DELETE)
	public BonLivraison deleteColisFomBonLivraison(@PathVariable String blId,@RequestBody  List<Colis> colis) {
		return bonLivraisonService.deleteColisFomBonLivraison(blId, colis);
	}
	@RequestMapping(value="/findBonLivraisonByNom/{nom}",method=RequestMethod.GET)
	public BonLivraison findBonLivraisonByNom(@PathVariable String nom) {
		return bonLivraisonService.findBonLivraisonByNom(nom);
	}
	@RequestMapping(value="/findMyBonLivraisonByMc",method=RequestMethod.GET)
	public Page<BonLivraison> findMyBonLivraisonByMc(@RequestParam(name="cni")String cni,@RequestParam(name="mc") String mc, @RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return bonLivraisonService.findMyBonLivraisonByMc(cni, mc, page, size);
	}

	@RequestMapping(value="/findAllBonLivraisonByMc",method=RequestMethod.GET)
	public Page<BonLivraison> findAllBonLivraisonByMc(@RequestParam(name="mc")String mc, @RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return bonLivraisonService.findAllBonLivraisonByMc(mc, page, size);
	}

	@RequestMapping(value="/updateStatutBonLivraison/{nom}",method=RequestMethod.PUT)
	public BonLivraison updateStatutBonLivraison(@PathVariable String nom,@RequestBody String statut) throws BonLivraisonException {
		return bonLivraisonService.updateStatutBonLivraison(nom, statut);
	}

	@RequestMapping(value="/deleteBonLivraison/{nom}",method=RequestMethod.DELETE)
	public void deleteBonLivraison(@PathVariable String nom) throws BonLivraisonException {
		bonLivraisonService.deleteBonLivraison(nom);
	}


	@RequestMapping(value="/generateBonLivraison",method=RequestMethod.POST)
	public BonLivraison generateBonLivraison(@RequestBody List<Colis> colis) {
		return bonLivraisonService.generateBonLivraison(colis);
	}

	@RequestMapping(value="/getBonLivraisons",method=RequestMethod.GET)
	public List<BonLivraison> getBonLivraisons(){
		return bonLivraisonService.findAll();
	}
	
	@RequestMapping(value="/getBonLivraison/{nom}",method=RequestMethod.GET)
	public BonLivraison getBonLivraison(@PathVariable String nom) {
		return bonLivraisonService.findBonLivraisonByNom(nom);
	}
	
	@RequestMapping(value="/editBonLivraisons/{nom}",method=RequestMethod.PUT)
	public BonLivraison editBonLivraison(@PathVariable String nom,@RequestBody BonLivraison c) throws BonLivraisonException {
		return bonLivraisonService.updateBonLivraison(nom, c);
	}
	
	@PostMapping(value="/bonLivraisons")
	public BonLivraison saveBonLivraison(@RequestBody BonLivraison bl) throws BonLivraisonException {
		return bonLivraisonService.saveBonLivraison(bl);
	}
	
	@RequestMapping(value="/bonLivraisons/{nom}",method=RequestMethod.DELETE)
	public boolean supprimerBonLivraison(@PathVariable String nom) throws BonLivraisonException {
		 bonLivraisonService.deleteBonLivraison(nom);
		 return true;
	}
	
	@RequestMapping(value="/chercherBonLivraisons",method=RequestMethod.GET)
	public Page<BonLivraison> chercherBonLivraison(@RequestParam(name="mc",defaultValue="") String mc,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return bonLivraisonService.findAllBonLivraisonByMc(mc, page, size);
	}
	
	@RequestMapping(value="/chercherBonLivraisonsByClient",method=RequestMethod.GET)
	public Page<BonLivraison> findAllBonLivraisonsByClient(@RequestParam(name="email")String emailClient, int page, int size) {
		return bonLivraisonService.findAllBonLivraisonsByClient(emailClient, page, size);
	}
	@RequestMapping(value="/chercherBonLivraisonsByUtilisateur",method=RequestMethod.GET)
	public Page<BonLivraison> findAllBonLivraisonsByUtilisateurs(@RequestParam(name="email")String emailUtilisateur, int page, int size) {
		return bonLivraisonService.findAllBonLivraisonsByUtilisateurs(emailUtilisateur, page, size);
	}
}
