package com.xpr.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.xpr.entities.Service;


@RepositoryRestResource(collectionResourceRel = "services", path = "services")
public interface ServiceRepository extends JpaRepository<Service, Long> {
	
	public Service findByNom(String nomService);

}
