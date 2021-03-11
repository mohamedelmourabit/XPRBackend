package com.xpr.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.xpr.entities.BonExpedition;

public interface BonExpeditionRepository extends JpaRepository<BonExpedition, Long> {
	
	@Query("select count(b) from BonExpedition b")
	public int getCountBonExpeditions();
	
	@Query("select b from BonExpedition b order by b.dateCreation DESC")
	public Page<BonExpedition> getAll(Pageable pageable);
	
	@Query("select b from BonExpedition b join b.creerPar l where l.email=:emailUtilisateur and b.disabled=false order by b.dateCreation DESC")
	public Page<BonExpedition> getAllBonExpeditionsUtilisateur(@Param("emailUtilisateur")String emailUtilisateur,Pageable pageable);

	

}
