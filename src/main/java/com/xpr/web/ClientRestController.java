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
import com.xpr.entities.Client;
import com.xpr.exceptions.ClientException;
import com.xpr.services.ClientService;

@RestController
public class ClientRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientRestController.class);
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value="/clients",method=RequestMethod.GET)
	public List<Client> getClients(){
		return clientService.findAll();
	}
	
	@RequestMapping(value="/clients/{email}",method=RequestMethod.GET)
	public Client getClient(@PathVariable String email) {
		try {
			return clientService.findClientByEmail(email);
		} catch (ClientException e) {
			LOGGER.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/clients/{email}",method=RequestMethod.PUT)
	public Client editClient(@PathVariable String email,@RequestBody Client c) {
		
		return clientService.updateClient(email, c);
	}
	
	@PostMapping(value="/clients")
	public Client saveClient(@RequestBody Client bl) {
		return clientService.saveClient(bl);
	}
	
	@RequestMapping(value="/clients/{nom}",method=RequestMethod.DELETE)
	public boolean supprimerClient(@PathVariable String nom) {
		 clientService.deleteClient(nom);
		 return true;
	}
	
	@RequestMapping(value="/chercherClients",method=RequestMethod.GET)
	public Page<Client> chercherClient(@RequestParam(name="mc",defaultValue="") String mc,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return clientService.findAllClientByMc(mc, page, size);
	}
}
