package com.xpr.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.xpr.entities.Agence;



@RepositoryRestResource(collectionResourceRel = "agences", path = "agences")
public interface AgenceRepository extends JpaRepository<Agence, Long> {
	
	public Agence findByNom(String nomAgence);
		
	
}
