package com.xpr.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.xpr.entities.Client;

public interface ClientRepository extends JpaRepository<Client, String> {
	
	
	@Query("select c from Client c where c.disabled=false and c.nom like '%'+:nom+'%'")
	public Page<Client> findClientByNom(@Param("nom") String nom, Pageable pageable);
	


}
