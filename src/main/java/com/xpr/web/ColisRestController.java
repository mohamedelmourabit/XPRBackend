package com.xpr.web;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.xpr.entities.Colis;
import com.xpr.exceptions.ColisException;
import com.xpr.exceptions.LivreurException;
import com.xpr.services.ColisService;


@RestController
public class ColisRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ColisRestController.class);
	
	@Autowired
	private ColisService colisService;
	
	
	@RequestMapping(value="/updateStatutColis",method=RequestMethod.PUT)
	public Colis updateStatutColis(@RequestParam(name="numCommande") String numCommande, @RequestParam(name="statut")String statut) throws ColisException {
		return colisService.updateStatutColis(numCommande, statut);
	}

	@RequestMapping(value="/deleteColis/{numCommande}",method=RequestMethod.DELETE)
	public void deleteColis(@PathVariable String numCommande) throws ColisException {
		colisService.deleteColis(numCommande);
	}

	@RequestMapping(value="/affectationMultipleColisAuLivreur/{cniLivreur}",method=RequestMethod.PUT)
	public List<Colis> affectationColisToLivreur(@PathVariable String cniLivreur, List<Colis> colis) throws LivreurException {
		return colisService.affectationColisToLivreur(cniLivreur, colis);
	}
	@RequestMapping(value="/affectationColisAuLivreur/{cniLivreur}",method=RequestMethod.PUT)
	public Colis affectationColisToLivreur(String cniLivreur, Colis colis) throws LivreurException {
		return colisService.affectationColisToLivreur(cniLivreur, colis);
	}
	
	@RequestMapping(value="/desaffectationMultipleColisAuLivreur/{cniLivreur}",method=RequestMethod.PUT)
	public List<Colis> desaffectationColisToLivreur(@PathVariable String cniLivreur, List<Colis> colis) throws LivreurException {
		return colisService.desaffectationColisToLivreur(cniLivreur, colis);
	}

	@RequestMapping(value="/desaffectationColisAuLivreur/{cniLivreur}",method=RequestMethod.PUT)
	public Colis desaffectationColisToLivreur(@PathVariable String cniLivreur, Colis colis) throws LivreurException {
		return colisService.desaffectationColisToLivreur(cniLivreur, colis);
	}

	@RequestMapping(value="/affectationColisAuRamasseur/{cniRamasseur}",method=RequestMethod.PUT)
	public Colis affectationColisToRamasseur(@PathVariable String cniRamasseur, Colis colis) throws LivreurException {
		return colisService.affectationColisToRamasseur(cniRamasseur, colis);
	}
	
	@RequestMapping(value="/getAllColisByLivreur/{cniLivreur}",method=RequestMethod.GET)
	public Page<Colis> getAllColisByLivreur(@PathVariable String cniLivreur, @RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return colisService.getAllColisByLivreur(cniLivreur, PageRequest.of(page, size));
	}

	@RequestMapping(value="/getAllColisByClient/{cniClient}",method=RequestMethod.GET)
	public Page<Colis> getAllColisByClient( @PathVariable String cniClient, @RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return colisService.getAllColisByClient(cniClient, PageRequest.of(page, size));
	}
	@RequestMapping(value="/getAllColis",method=RequestMethod.GET)
	public Page<Colis> getAllColisUtilisateur(@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return colisService.getAllColisUtilisateur(PageRequest.of(page, size));
	}

	public List<Colis> getAllColisWithoutBonRamassage() {
		return colisService.getAllColisWithoutBonRamassage();
	}

	public List<Colis> getAllColisWithoutBonLivraison() {
		return colisService.getAllColisWithoutBonLivraison();
	}

	public List<Colis> getAllColisWithoutBonExpedition() {
		return colisService.getAllColisWithoutBonExpedition();
	}

	public List<Colis> getAllColisWithBonRetour() {
		return colisService.getAllColisWithBonRetour();
	}

	@RequestMapping(value="/colisByStatut",method=RequestMethod.GET)
	public Page<Colis> getAllColisByStatut(String statut, @RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return colisService.getAllColisByStatut(statut, PageRequest.of(page, size));
	}

	@RequestMapping(value="/colis",method=RequestMethod.GET)
	public List<Colis> getColis(){
		return colisService.findAll();
	}
	
	@RequestMapping(value="/colis/{numCommande}",method=RequestMethod.GET)
	public Colis getColis(@PathVariable String numCommande) {
		return colisService.findColisById(numCommande);
	}
	
	@RequestMapping(value="/colis/{numCommande}",method=RequestMethod.PUT)
	public Colis editColis(@PathVariable String numCommande,@RequestBody Colis c) throws ColisException {
		
		return colisService.updateColis(numCommande, c);
	}
	
	@PostMapping(value="/colis")
	public Colis saveColisService(@RequestBody Colis colis) {
		return colisService.saveColis(colis);
	}
	
	@RequestMapping(value="/colis/{numCommande}",method=RequestMethod.DELETE)
	public boolean supprimerColis(@PathVariable String numCommande) throws ColisException {
		 colisService.deleteColis(numCommande); 
		 return true;
	}
	
	@RequestMapping(value="/chercherColis",method=RequestMethod.GET)
	public Page<Colis> chercherColisService(@RequestParam(name="mc",defaultValue="") String mc,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return colisService.findAllColisByMc(mc, page,size);
	}
	
	@RequestMapping(value="/chercherColisByLivreur",method=RequestMethod.GET)
	public Page<Colis> findAllColisByLivreur(@RequestParam(name="cni")String cniLivreur,@RequestParam(name="page",defaultValue="0") int page,@RequestParam(name="size",defaultValue="5") int size) {
		return colisService.findAllColisByLivreur(cniLivreur, page, size);
	}

	@RequestMapping(value="/chercherColisByClient",method=RequestMethod.GET)
	public Page<Colis> findAllColisByClient(@RequestParam(name="email")String cniClient,@RequestParam(name="page",defaultValue="0") int page,@RequestParam(name="size",defaultValue="5") int size) {
		return colisService.findAllColisByClient(cniClient, page, size);
	}

	@RequestMapping(value="/chercherColisByUtilisateurs",method=RequestMethod.GET)
	public Page<Colis> findAllColisByUtilisateurs(@RequestParam(name="email")String emailUtilisateur,@RequestParam(name="page",defaultValue="0") int page,@RequestParam(name="size",defaultValue="5") int size) {
		return colisService.findAllColisByUtilisateurs(emailUtilisateur, page, size);
	}
}
