package com.xpr.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.xpr.entities.Colis;

public interface ColisRepository extends JpaRepository<Colis, String> {
	
	@Query("select c from Colis c where c.codeEnvoi like :codeEnvoi order by c.dateCreation DESC")
	public Page<Colis> findByNameStartsWith(@Param("codeEnvoi") String codeEnvoi, Pageable pageable);
	@Query("select count(c) from Colis c")
	public int getCountColis();
	
	@Query("select count(c) from Colis c where c.disabled = false and c.statut=:statut")
	public int getCountColisByStatut(@Param("statut") String statut);
	
	@Query("select count(c) from Colis c where c.disabled = false and c.statut=:statut and c.dateCreation >= :dateDebut and c.dateCreation <= :dateFin ")
	public int getColisByStatutAndDate(@Param("statut") String statut,@Param("dateDebut") Date dateDebut,@Param("dateFin") Date dateFin);
	
	@Query("select c from Colis c where c.disabled=false order by c.dateCreation DESC")
	public Page<Colis> getAll(Pageable pageable);
	
	@Query("select c from Colis c join c.livreur l where l.email=:emailLivreur and c.disabled=false order by c.dateCreation DESC")
	public Page<Colis> getAllColisByLivreur(@Param("emailLivreur")String emailLivreur,Pageable pageable);
	
	@Query("select c from Colis c join c.client l where l.email =:emailClient and c.disabled=false order by c.dateCreation DESC")
	public Page<Colis> getAllColisByClient(@Param("emailClient")String emailClient,Pageable pageable);
	
	@Query("select c from Colis c where c.disabled=false order by c.dateCreation DESC")
	public Page<Colis> getAllColisUtilisateur(Pageable pageable);
	
	
	@Query("select c from Colis c join c.bonRamassage l where l is null and c.disabled=false order by c.dateCreation DESC")
	public List<Colis> getAllColisWithoutBonRamassage();
	
	@Query("select c from Colis c join c.bonLivraison l where l is null and c.disabled=false order by c.dateCreation DESC")
	public List<Colis> getAllColisWithoutBonLivraison();
	
	@Query("select c from Colis c join c.bonExpedition l where l is null and c.disabled=false order by c.dateCreation DESC")
	public List<Colis> getAllColisWithoutBonExpedition();
	
	@Query("select c from Colis c join c.bonRetour l where l is not null and c.disabled=false order by c.dateCreation DESC")
	public List<Colis> getAllColisWithBonRetour();
	
	@Query("select c from Colis c where c.statut=:statut and c.disabled=false order by c.dateCreation DESC")
	public Page<Colis> getAllColisByStatut(@Param("statut") String statut ,Pageable pageable);
	

	

}
