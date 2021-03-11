package com.xpr.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.xpr.entities.Stock;


public interface StockRepository extends JpaRepository<Stock, Long> {
		
	@Query("SELECT s FROM Stock s WHERE s.client.ice=:ice  and s.produit.nom=:x")
	public List<Stock> findStock(@Param("ice")String iceClient,@Param("x")String nomProduit);
	
	@Query("SELECT s FROM Stock s WHERE s.client.ice=:ice and s.produit.nom=:x and s.ville.nom =:v")
	public List<Stock> findStockByClientAndNomProditAndLivreur(@Param("ice")String iceClient,@Param("x")String nomProduit,@Param("v")String ville);
	
	@Query("SELECT s FROM Stock s WHERE s.client.ice=:ice  and s.livreur.cni=:x")
	public List<Stock> findStockByClientAndLivreur(@Param("ice")String iceClient,@Param("x")String cniLivreur);
	
	@Query("SELECT s FROM Stock s WHERE s.client.ice=:ice  and s.ville.nom =:v")
	public List<Stock> findStockByClientAndLivreurAndVille(@Param("ice")String iceClient,@Param("v")String ville);
	
	
	

}
