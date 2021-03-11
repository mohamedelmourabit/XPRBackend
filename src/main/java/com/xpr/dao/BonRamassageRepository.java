package com.xpr.dao;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.xpr.entities.BonRamassage;




public interface BonRamassageRepository extends JpaRepository<BonRamassage, Long> {
	
	public BonRamassage findByNom(String nom);
	
	@Query("SELECT b FROM BonRamassage b WHERE b.nom=:x")
	public List<BonRamassage> findBonRamassage(@Param("x")String nomBonRamassage);
	
	@Query("select b from BonRamassage b where b.client.nom=:client order by b.nom ASC")
	public Page<BonRamassage> searchBonRamassageByClient(@Param("client") String client, Pageable pageable);
	
	@Query("select b from BonRamassage b order by b.nom ASC")
	public Page<BonRamassage> getAll( Pageable pageable);
	
	@Query("select b from BonRamassage b join b.client l where l.cni =:cni and b.disabled=false order by b.dateCreation DESC")
	public Page<BonRamassage> getAllBonRamassageByClient(@Param("cni")String cniClient,Pageable pageable);
	
	@Query("select b from BonRamassage b join b.creerPar l where l.cni=:cniUtilisateur and b.disabled=false order by b.dateCreation DESC")
	public Page<BonRamassage> getAllBonRamassagesUtilisateur(@Param("cniUtilisateur")String cniUtilisateur,Pageable pageable);

	
	@Query("select b from BonRamassage b where b.nom like '%'+:nom+'%' order by b.dateCreation DESC")
	public Page<BonRamassage> findByNameStartsWith(@Param("nom") String nom, Pageable pageable);
	
	@Query("select b from BonRamassage b where b.nom like '%'+:nom+'%' and b.client.cni=:cni order by b.dateCreation DESC")
	public Page<BonRamassage> findByCniAndNomStartsWith(@Param("nom") String nom,@Param("cni") String cni, Pageable pageable);
	
	@Query("select count(b) from BonRamassage b")
	public int getCountBonRamassages();
	
}
