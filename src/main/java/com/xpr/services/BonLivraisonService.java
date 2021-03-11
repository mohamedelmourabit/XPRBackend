package com.xpr.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.xpr.entities.BonLivraison;
import com.xpr.entities.Colis;
import com.xpr.exceptions.BonLivraisonException;


public interface BonLivraisonService {
	
	public BonLivraison saveBonLivraison(BonLivraison bl) throws BonLivraisonException;
	
	public BonLivraison addColisToBonLivraison(String blId,List<Colis> colis) throws BonLivraisonException;
	
	public BonLivraison deleteColisFomBonLivraison(String blId,List<Colis> colis);
	
	public List<Colis> findColisFomBonLivraison(String blId);

	public BonLivraison findBonLivraisonByNom(String nom);

	public Page<BonLivraison> findMyBonLivraisonByMc(String email, String mc, int page, int size);

	public Page<BonLivraison> findAllBonLivraisonByMc(String mc, int page, int size);

	public List<BonLivraison> findAll();

	public BonLivraison updateBonLivraison(String nom, BonLivraison bonLivraison) throws BonLivraisonException;

	public BonLivraison updateStatutBonLivraison(String nom, String staut) throws BonLivraisonException;
	
	public void deleteBonLivraison(String nom) throws BonLivraisonException;
	
	public BonLivraison generateBonLivraison(List<Colis> colis);
	
	public Page<BonLivraison> findAllBonLivraisonsByClient(String emailClient, int page, int size);
	
	public Page<BonLivraison> findAllBonLivraisonsByUtilisateurs(String emailUtilisateur, int page, int size);

	public int getCountBonLivraisons();
}
