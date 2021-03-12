package com.xpr.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.xpr.entities.BonExpedition;
import com.xpr.entities.BonLivraison;
import com.xpr.entities.BonRamassage;

public interface BonExpeditionRepository extends JpaRepository<BonExpedition, Long> {
	
	public BonExpedition findByNom(String nom);
	
	@Query("select count(b) from BonExpedition b")
	public int getCountBonExpeditions();
	
	@Query("select b from BonExpedition b order by b.dateCreation DESC")
	public Page<BonExpedition> getAll(Pageable pageable);
	
	@Query("select b from BonExpedition b join b.creerPar l where l.email=:emailUtilisateur and b.disabled=false order by b.dateCreation DESC")
	public Page<BonExpedition> getAllBonExpeditionsUtilisateur(@Param("emailUtilisateur")String emailUtilisateur,Pageable pageable);

	@Query("select b from BonExpedition b where b.nom like '%'+:nom+'%' and b.client.cni=:cni order by b.dateCreation DESC")
	public Page<BonExpedition> findByCniAndNomStartsWith(@Param("nom") String nom,@Param("cni") String cni, Pageable pageable);

	@Query("select b from BonExpedition b where b.nom like '%'+:nom+'%' order by b.dateCreation DESC")
	public Page<BonExpedition> findByNameStartsWith(@Param("nom") String nom, Pageable pageable);
	
	@Query("select b from BonExpedition b join b.client l where l.cni =:cni and b.disabled=false order by b.dateCreation DESC")
	public Page<BonExpedition> getAllBonExpeditionByClient(@Param("cni")String cniClient,Pageable pageable);
	
	
}
