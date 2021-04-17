package com.xpr.dao.specification;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import com.xpr.dao.helper.GenericSearchSpecification;
import com.xpr.dto.ColisSearch;
import com.xpr.entities.Colis;


public class ColisSpecification extends GenericSearchSpecification<Colis> implements Specification<Colis> {
	
	private ColisSearch colisSearch;
	
	
	public ColisSpecification(ColisSearch colisSearch) {
		super(null, null);
		this.colisSearch = colisSearch;
	}

	@Override
	public Predicate toPredicate(Root<Colis> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		final List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(cb.equal(root.get("disabled"),false));
		
		if(colisSearch.getNumCommande() !=null) {
			predicates.add(cb.equal(root.get("numCommande"),colisSearch.getNumCommande()));
		}
		
		if(colisSearch.getMc()!=null) {
			Predicate numCommande = cb.like(root.get("numCommande"), "%"+colisSearch.getMc()+"%");
			
			Predicate codeEnvoi = cb.like(root.get("codeEnvoi"), "%"+colisSearch.getMc()+"%");
			
			Predicate idIntern = cb.like(root.get("idIntern"), "%"+colisSearch.getMc()+"%");
		
			
			Predicate typeLivraison = cb.like(root.get("typeLivraison"), "%"+colisSearch.getMc()+"%");
			
			Predicate nomComplet = cb.like(root.get("nomComplet"), "%"+colisSearch.getMc()+"%");
			
			Predicate destinataire = cb.like(root.get("destinataire"), "%"+colisSearch.getMc()+"%");
			
			Predicate telephone = cb.like(root.get("telephone"), "%"+colisSearch.getMc()+"%");
			
			Predicate villeDestination = cb.like(root.join("villeDestination").get("nom"), "%"+colisSearch.getMc()+"%");
			
			Predicate secteur = cb.like(root.get("secteur"), "%"+colisSearch.getMc()+"%");
			
			Predicate adresse = cb.like(root.get("adresse"), "%"+colisSearch.getMc()+"%");
			
			Predicate clientName = cb.like(root.join("client").get("nom"), "%"+colisSearch.getMc()+"%");
			
			Predicate clientId = cb.like(root.join("client").get("ice"), "%"+colisSearch.getMc()+"%");
			
			
			Predicate statut = cb.like(root.join("statut").get("libelle"), "%"+colisSearch.getMc()+"%");
			
			Predicate produit = cb.like(root.join("ligneColis").join("produit").get("nom"), "%"+colisSearch.getMc()+"%");
			
			Predicate produitFromVariante = cb.like(root.join("ligneColis").join("variante").join("produit").get("nom"), "%"+colisSearch.getMc()+"%");
			
			
			Predicate varianteSku = cb.like(root.join("ligneColis").join("variante").get("sku"), "%"+colisSearch.getMc()+"%");
			
			Predicate prix = cb.like(root.join("ligneColis").get("prix").as(String.class), "%"+colisSearch.getMc()+"%");
			
			Predicate entiteName = cb.like(root.join("entite").get("nom"), "%"+colisSearch.getMc()+"%");
			
			Predicate entiteId = cb.like(root.join("entite").get("id").as(String.class), "%"+colisSearch.getMc()+"%");
			
			
			predicates.add(cb.or(numCommande,codeEnvoi,idIntern,typeLivraison,
					nomComplet,destinataire,telephone,villeDestination,secteur
					,adresse,clientName,clientId,statut,produit,produitFromVariante,varianteSku,prix,entiteName,entiteId));
		}
		
        if(colisSearch.getNumCommande() !=null) {
            predicates.add(cb.equal(root.get("numCommande"),colisSearch.getNumCommande()));
        }
        if(colisSearch.getCodeEnvoi()!=null) {
            predicates.add(cb.equal(root.get("numMarche"),colisSearch.getCodeEnvoi()));
        }
        
        if(colisSearch.getIdIntern()!=null) {
            predicates.add(cb.equal(root.get("idIntern"),colisSearch.getIdIntern()));
        }
        if(colisSearch.getTypeLivraison()!=null) {
            predicates.add(cb.equal(root.get("typeLivraison"),colisSearch.getTypeLivraison()));
        }
        
        if(colisSearch.getNomComplet() !=null) {
            predicates.add(cb.equal(root.get("nomComplet"),colisSearch.getNomComplet()));
        }
        
        if(colisSearch.getDestinataire() !=null) {
            predicates.add(cb.equal(root.get("destinataire"),colisSearch.getDestinataire()));
        }
        
        if(colisSearch.getTelephone() !=null) {
            predicates.add(cb.equal(root.get("nomComplet"),colisSearch.getTelephone()));
        }
        
        if(colisSearch.getVilleDestination() !=null) {
            predicates.add(cb.equal(root.join("villeDestination").get("nom"),colisSearch.getVilleDestination().getNom()));
        }
        
        if(colisSearch.getSecteur() !=null) {
            predicates.add(cb.equal(root.get("secteur"),colisSearch.getSecteur()));
        }
        
        if(colisSearch.getAdresse() !=null) {
            predicates.add(cb.equal(root.get("adresse"),colisSearch.getAdresse()));
        }
        
        if(colisSearch.getClient() !=null) {
        	
        	
        	if(colisSearch.getClient().getIce()!=null) {
        		 predicates.add(cb.equal(root.join("client").get("ice"),colisSearch.getClient().getIce()));
        	        
        	}
        	if(colisSearch.getClient().getNom()!=null) {
        		 predicates.add(cb.equal(root.join("client").get("nom"),colisSearch.getClient().getNom()));    
        	}
        }
        
        if(colisSearch.getStatut() !=null) {
            predicates.add(cb.equal(root.join("statut").get("libelle"),colisSearch.getStatut()));
        }
        
        if(colisSearch.getProduitName()!=null) {
        	
        	
           predicates.add(cb.equal(root.join("ligneColis").join("produit").get("nom"),colisSearch.getProduitName()));
        		
           predicates.add(cb.equal(root.join("ligneColis").join("variante").join("produit").get("nom"),colisSearch.getProduitName()));
                  			
        }
        
        if(colisSearch.getVarianteName()!=null) {
        	
           predicates.add(cb.equal(root.join("ligneColis").join("variante").get("sku"),colisSearch.getVarianteName()));
               							 
        }
        
        if(colisSearch.getPrix() !=null) {
            predicates.add(cb.equal(root.join("ligneColis").get("prix").as(String.class),colisSearch.getPrix()));
        }
        
        			 
        if(colisSearch.getEntite()!=null) {
        	
        	if(colisSearch.getEntite().getId()!=null) {
       		 predicates.add(cb.equal(root.join("entite").get("id").as(String.class),colisSearch.getEntite().getId()));
                
        	}
        	
        	if(colisSearch.getEntite().getNom()!=null) {
        		 predicates.add(cb.equal(root.join("entite").get("nom").as(String.class),colisSearch.getEntite().getNom()));
                 
        	}
        	
        }
           
		
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}

	
}
