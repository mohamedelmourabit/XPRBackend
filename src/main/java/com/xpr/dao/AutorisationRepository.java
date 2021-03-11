package com.xpr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xpr.entities.Autorisation;

public interface AutorisationRepository extends JpaRepository<Autorisation, Long> {
	
	public Autorisation findByAuthName(String authName);

}
