package com.xpr.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import com.xpr.entities.Profile;



public interface AgenceRepository extends JpaRepository<Profile, Long> {
	
	public Profile findByPrflName(String prflName);
		

}
