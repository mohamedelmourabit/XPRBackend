package com.xpr.dao;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.xpr.entities.Agence;
import com.xpr.entities.Autorisation;
import com.xpr.entities.Profile;



public interface ProfileRepository extends JpaRepository<Profile, Long> {
	

	public Profile findByPrflName(String profileName);
	
	@Query("select p from Profile p join p.utilisateurs up on up.cni = :x")
	public List<Profile> findProfilesByUtilisateur(@Param("x")String cni);
	
	@Query("select p.authorities from Profile p where p.prflName= :x")
	public List<Autorisation> findAuthorityByPrflName(@Param("x") String profileName);
	
	

}
