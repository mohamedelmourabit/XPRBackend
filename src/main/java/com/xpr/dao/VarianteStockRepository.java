package com.xpr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.xpr.entities.VarianteStock;

@RepositoryRestResource(collectionResourceRel = "variantesStock", path = "variantesStock")
public interface VarianteStockRepository extends JpaRepository<VarianteStock, Long> {

	
	@Query("select sum(v.qte) from VarianteStock v where v.variante.sku=:sku")
	public int getSumQteStockVariante(@Param("sku") String sku);
}
