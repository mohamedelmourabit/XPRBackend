package com.xpr.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.xpr.entities.BonLivraison;

public interface BonLivraisonRepository extends JpaRepository<BonLivraison, String> {
	
	@Query("select count(b) from BonLivraison b")
	public int getCountBonLivraisons();
	
	@Query("select b from BonLivraison b where b.nom like '%'+:nom+'%' order by b.dateCreation DESC")
	public Page<BonLivraison> findByNameStartsWith(@Param("nom") String nom, Pageable pageable);
	
	@Query("select b from BonLivraison b where b.nom like '%'+:nom+'%' and b.client.cni=:cni order by b.dateCreation DESC")
	public Page<BonLivraison> findByCniAndNomStartsWith(@Param("nom") String nom,@Param("cni") String cni, Pageable pageable);
	
	
	@Query("select b from BonLivraison b order by b.dateCreation DESC")
	public Page<BonLivraison> getAll(Pageable pageable);
	
	@Query("select b from BonLivraison b join b.client l where l.cni =:cni and b.disabled=false order by b.dateCreation DESC")
	public Page<BonLivraison> getAllBonLivraisonByClient(@Param("cni")String cniClient,Pageable pageable);
	
	@Query("select b from BonLivraison b join b.creerPar l where l.cni=:cniUtilisateur and b.disabled=false order by b.dateCreation DESC")
	public Page<BonLivraison> getAllBonLivraisonsUtilisateur(@Param("cniUtilisateur")String emailUtilisateur,Pageable pageable);

	

}
