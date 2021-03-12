package com.xpr.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xpr.entities.BonExpedition;
import com.xpr.entities.BonRamassage;
import com.xpr.entities.BonRetour;

public interface BonRetourRepository extends JpaRepository<BonRetour, String> {
	
	public BonRetour findByNom(String nom);
	
	@Query("select count(b) from BonRetour b")
	public int getCountBonRetours();
	
	@Query("select b from BonRetour b where b.nom like '%'+:nom+'%' order by b.dateCreation DESC")
	public Page<BonRetour> findByNameStartsWith(@Param("nom") String nom, Pageable pageable);
	
	@Query("select b from BonRetour b where b.nom like '%'+:nom+'%' and b.client.cni=:cni order by b.dateCreation DESC")
	public Page<BonRetour> findByCniAndNomStartsWith(@Param("nom") String nom,@Param("cni") String cni, Pageable pageable);
	
	
	@Query("select b from BonRetour b order by b.dateCreation DESC")
	public Page<BonRetour> getAll(Pageable pageable);
	
	@Query("select b from BonRetour b join b.client l where l.cni =:cni and b.disabled=false order by b.dateCreation DESC")
	public Page<BonRetour> getAllBonRetourByClient(@Param("cni")String cniClient,Pageable pageable);
	
	@Query("select b from BonRetour b join b.creerPar l where l.cni=:cniUtilisateur and b.disabled=false order by b.dateCreation DESC")
	public Page<BonRetour> getAllBonRetoursUtilisateur(@Param("cniUtilisateur")String emailUtilisateur,Pageable pageable);

	
	@Query("select b from BonRetour b join b.client l where l.cni =:cni and b.disabled=false order by b.dateCreation DESC")
	public Page<BonRetour> getAllBonExpeditionByClient(@Param("cni")String cniClient,Pageable pageable);
	
	

}
