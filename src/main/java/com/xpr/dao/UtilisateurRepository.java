package com.xpr.dao;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.xpr.entities.Utilisateur;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {
	
	public Utilisateur findByEmail(String email);
	

	
	@Query("SELECT u FROM Utilisateur u  WHERE u.cni=:x")
	public Utilisateur findByCni(@Param("x")String cni);
	
	@Query("SELECT u.authorities FROM Utilisateur u  WHERE u.cni=:x")
	public List<com.xpr.entities.Autorisation> findUserAuthority(@Param("x")String cni);
	
	@Query("select u from Utilisateur u where u.email like :text")
	public Page<Utilisateur> searchUser(@Param("text") String text, Pageable pageable);
	
	@Query("select u from Utilisateur u order by u.email ASC")
	public Page<Utilisateur> getAll( Pageable pageable);
	

}
