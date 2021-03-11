package com.xpr.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.xpr.entities.Demande;

public interface DemandeRepository extends JpaRepository<Demande, String> {
	
	
	@Query("select d from Demande d where  d.nom like '%'+:nom+'%' order by d.dateCreation DESC")
	public Page<Demande> findByNameStartsWith(@Param("nom") String nom, Pageable pageable);
	
	@Query("select count(d) from Demande d where d.disabled = false")
	public int getCountDemandes();
	
	@Query("select d from Demande d where d.disabled=false  order by d.dateCreation DESC")
	public Page<Demande> getAll(Pageable pageable);
	
	@Query("select d from Demande d join d.client l where l.email =:emailClient and d.disabled=false order by d.dateCreation DESC")
	public Page<Demande> getAllDemandesByClient(@Param("emailClient")String emailClient,Pageable pageable);
	
	@Query("select d from Demande d join d.creerPar l where l.email=:emailUtilisateur and d.disabled=false order by d.dateCreation DESC")
	public Page<Demande> getAllDemandesUtilisateur(@Param("emailUtilisateur")String emailUtilisateur,Pageable pageable);

	


}
