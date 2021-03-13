package com.xpr.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.xpr.entities.Autorisation;

@RepositoryRestResource(collectionResourceRel = "autorisations", path = "autorisations")
public interface AutorisationRepository extends JpaRepository<Autorisation, Long> {
	
	public Autorisation findByAuthName(String authName);
	
	@Query("select a from Autorisation a order by id ASC")
	public Page<Autorisation> getAllAutorisations(Pageable pageable);
	
	
}
