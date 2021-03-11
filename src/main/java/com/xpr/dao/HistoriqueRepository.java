package com.xpr.dao;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.xpr.entities.Historique;



public interface HistoriqueRepository extends JpaRepository<Historique, Long> {
	
	
	@Query("SELECT h FROM Historique h  WHERE h.utilisateur.cni=:x order by h.dateCreation ASC")
	public List<Historique> findHistoriqueUtilisateurByCNI(@Param("x")String cni);
	
	@Query("SELECT h FROM Historique h  WHERE h.utilisateur.cni=:x order by h.dateCreation ASC")
	public Page<Historique> findHistoriqueUtilisateurByCNI(@Param("x")String cni, Pageable pageable);
	
	@Query("SELECT h FROM Historique h  WHERE h.colis.numCommande=:x")
	public List<Historique> findHistoriqueColisByNom(@Param("x")String nomColis);
	
	@Query("SELECT h FROM Historique h  WHERE h.colis.numCommande=:x")
	public Page<Historique> findHistoriqueColisByNumCommande(@Param("x")String numCommande, Pageable pageable);
	
	@Query("SELECT h FROM Historique h WHERE h.bonLivraison.nom=:x")
	public List<Historique> findHistoriqueBLByNom(@Param("x")String nomBL);
	
	@Query("SELECT h FROM Historique h WHERE h.bonLivraison.nom=:x")
	public Page<Historique> findHistoriqueBLByNom(@Param("x")String nomBL, Pageable pageable);
	
	@Query("SELECT h FROM Historique h WHERE h.bonRamassage.nom=:x")
	public List<Historique> findHistoriqueBonRamassageByNom(@Param("x")String nomBonRamassage);
	
	@Query("SELECT h FROM Historique h WHERE h.bonLivraison.nom=:x")
	public Page<Historique> findHistoriqueBonRamassageByNom(@Param("x")String nomBonRamassage, Pageable pageable);
	
	@Query("SELECT h FROM Historique h WHERE h.demande.nom=:x")
	public List<Historique> findHistoriqueDemandeByNom(@Param("x")String nomDemande);
	
	@Query("SELECT h FROM Historique h WHERE h.demande.nom=:x")
	public Page<Historique> findHistoriqueDemandeByNom(@Param("x")String nomDemande, Pageable pageable);
	

}
