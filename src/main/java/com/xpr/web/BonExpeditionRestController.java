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

import com.xpr.entities.BonExpedition;
import com.xpr.entities.Colis;
import com.xpr.entities.Historique;
import com.xpr.exceptions.BonExpeditionException;
import com.xpr.services.BonExpeditionService;

@RestController
public class BonExpeditionRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BonExpeditionRestController.class);
	
	@Autowired
	private BonExpeditionService bonExpeditionService;
	
	@RequestMapping(value="/getHistoriqueBonExpedition/{nom}",method=RequestMethod.GET)
	public List<Historique> getHistoriqueBonExpedition(String nom) {
		return bonExpeditionService.getHistoriqueBonExpedition(nom);
	}
	@RequestMapping(value="/getHistoriqueBonExpeditionPagination/{nom}",method=RequestMethod.GET)
	public Page<Historique> getHistoriqueBonExpedition(String nom, @RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return bonExpeditionService.getHistoriqueBonExpedition(nom, page, size);
	}
	@RequestMapping(value="/addColisToBonExpedition/{blId}",method=RequestMethod.PUT)
	public BonExpedition addColisToBonExpedition(@PathVariable String blId,@RequestBody  List<Colis> colis) throws BonExpeditionException {
		return bonExpeditionService.addColisToBonExpedition(blId, colis);
	}
	@RequestMapping(value="/deleteColisFomBonExpedition/{blId}",method=RequestMethod.DELETE)
	public BonExpedition deleteColisFomBonExpedition(@PathVariable String blId,@RequestBody  List<Colis> colis) {
		return bonExpeditionService.deleteColisFomBonExpedition(blId, colis);
	}
	@RequestMapping(value="/findBonExpeditionById/{nom}",method=RequestMethod.GET)
	public BonExpedition findBonExpeditionByNom(@PathVariable String nom) {
		return bonExpeditionService.findBonExpeditionByNom(nom);
	}
	@RequestMapping(value="/findMyBonExpeditionByMc",method=RequestMethod.GET)
	public Page<BonExpedition> findMyBonExpeditionByMc(@RequestParam(name="cni")String cni, @RequestParam(name="mc") String mc, @RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return bonExpeditionService.findMyBonExpeditionByMc(cni, mc, page, size);
	}

	@RequestMapping(value="/findAllBonExpeditionByMc",method=RequestMethod.GET)
	public Page<BonExpedition> findAllBonExpeditionByMc(@RequestParam(name="mc")String mc, @RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return bonExpeditionService.findAllBonExpeditionByMc(mc, page, size);
	}

	@RequestMapping(value="/updateStatutBonExpedition/{nom}",method=RequestMethod.PUT)
	public BonExpedition updateStatutBonExpedition(@PathVariable String nom,@RequestBody String statut) throws BonExpeditionException {
		return bonExpeditionService.updateStatutBonExpedition(nom, statut);
	}

	@RequestMapping(value="/deleteBonExpedition/{nom}",method=RequestMethod.DELETE)
	public void deleteBonExpedition(@PathVariable String nom) throws BonExpeditionException {
		bonExpeditionService.deleteBonExpedition(nom);
	}


	@RequestMapping(value="/generateBonExpedition",method=RequestMethod.POST)
	public BonExpedition generateBonExpedition(@RequestBody List<Colis> colis) {
		return bonExpeditionService.generateBonExpedition(colis);
	}

	@RequestMapping(value="/getBonExpeditions",method=RequestMethod.GET)
	public List<BonExpedition> getBonExpeditions(){
		return bonExpeditionService.findAll();
	}
	
	@RequestMapping(value="/bonExpeditions/{nom}",method=RequestMethod.GET)
	public BonExpedition getBonExpedition(@PathVariable String nom) {
		return bonExpeditionService.findBonExpeditionByNom(nom);
	}
	
	@RequestMapping(value="/editBonExpedition/{nom}",method=RequestMethod.PUT)
	public BonExpedition editBonExpedition(@PathVariable String nom,@RequestBody BonExpedition c) throws BonExpeditionException {
		return bonExpeditionService.updateBonExpedition(nom, c);
	}
	
	@PostMapping(value="/bonExpeditions")
	public BonExpedition saveBonExpedition(@RequestBody BonExpedition bl) throws BonExpeditionException {
		return bonExpeditionService.saveBonExpedition(bl);
	}
	
	@RequestMapping(value="/supprimerBonExpedition/{nom}",method=RequestMethod.DELETE)
	public boolean supprimerBonExpedition(@PathVariable String nom) throws BonExpeditionException {
		 bonExpeditionService.deleteBonExpedition(nom);
		 return true;
	}
	
	@RequestMapping(value="/chercherBonExpeditions",method=RequestMethod.GET)
	public Page<BonExpedition> chercherBonExpedition(@RequestParam(name="mc",defaultValue="") String mc,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return bonExpeditionService.findAllBonExpeditionByMc(mc, page, size);
	}
	
	@RequestMapping(value="/chercherBonExpeditionsByClient",method=RequestMethod.GET)
	public Page<BonExpedition> findAllBonExpeditionsByClient(@RequestParam(name="email")String emailClient, int page, int size) {
		return bonExpeditionService.findAllBonExpeditionsByClient(emailClient, page, size);
	}
	@RequestMapping(value="/chercherBonExpeditionsByUtilisateur",method=RequestMethod.GET)
	public Page<BonExpedition> findAllBonExpeditionsByUtilisateurs(@RequestParam(name="email")String emailUtilisateur, int page, int size) {
		return bonExpeditionService.findAllBonExpeditionsByUtilisateurs(emailUtilisateur, page, size);
	}
}
