package com.xpr.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import com.xpr.entities.Service;



public interface ServiceRepository extends JpaRepository<Service, Long> {
	
	public Service findByNom(String nomService);

}
