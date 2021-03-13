package com.xpr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.xpr.entities.Ville;

@RepositoryRestResource(collectionResourceRel = "villes", path = "villes")
public interface VilleRepository extends JpaRepository<Ville, String> {
		

}
