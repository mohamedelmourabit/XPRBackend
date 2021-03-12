package com.xpr.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.xpr.entities.Agence;




public interface AgenceRepository extends JpaRepository<Agence, Long> {
	
	public Agence findByNom(String nomAgence);
		
	
}
