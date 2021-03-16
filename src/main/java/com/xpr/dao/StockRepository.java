package com.xpr.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.xpr.entities.Stock;


public interface StockRepository extends JpaRepository<Stock, Long> {
		
/*	@Query("SELECT s FROM Stock s WHERE s.client.ice=:ice  and s.variante.produit.nom=:x")
	public List<Stock> findStockByClientAndProduitNom(@Param("ice")String iceClient,@Param("x")String nomProduit);
	
	@Query("SELECT s FROM Stock s WHERE s.client.ice=:ice and s.variante.produit.nom=:x and s.ville.nom =:v")
	public List<Stock> findStockByClientAndNomProditAndVille(@Param("ice")String iceClient,@Param("x")String nomProduit,@Param("v")String ville);
	
	@Query("SELECT s FROM Stock s WHERE s.client.ice=:ice  and s.variante.produit.reference=:x")
	public List<Stock> findStockByClientAndProduitReference(@Param("ice")String iceClient,@Param("x")String reference);*/
	
	@Query("SELECT s FROM Stock s WHERE s.client.ice=:ice  and s.variante.sku=:x")
	public List<Stock> findStockByClientAndVarianteSku(@Param("ice")String iceClient,@Param("x")String sku);
	
	@Query("SELECT s FROM Stock s WHERE s.client.ice=:ice and s.variante.sku=:x and s.ville.nom =:v")
	public List<Stock> findStockByClientAndVarianteSkuAndVille(@Param("ice")String iceClient,@Param("x")String sku,@Param("v")String ville);
	

	@Query("SELECT s FROM Stock s WHERE s.variante.sku=:x")
	public List<Stock> findStockVarianteSku(@Param("x")String sku);
	
	@Query("SELECT s FROM Stock s WHERE s.agence.id=:idAgence  and s.variante.sku=:x")
	public List<Stock> findStockByAgenceAndVarianteSku(@Param("idAgence")Long idAgence,@Param("x")String sku);
	

	@Query("SELECT s FROM Stock s WHERE s.livreur.cni=:x  and s.variante.sku=:x ")
	public List<Stock> findStockByLivreurAndVarianteSku(@Param("x")String cniLivreur,@Param("x")String sku);
	
	@Query("SELECT s FROM Stock s WHERE s.client.ice=:ice  and s.ville.nom =:v")
	public List<Stock> findStockByClientAndLivreurAndVille(@Param("ice")String iceClient,@Param("v")String ville);

	@Query("SELECT s FROM Stock s WHERE s.client.ice=:ice  and s.variante.produit.nature=:x")
	public List<Stock> findStockByClientAndProduitNature(@Param("ice")String iceClient,@Param("x")String nature);
	
	
	@Query("SELECT s FROM Stock s WHERE s.client.ice=:ice  and s.livreur.cni=:x")
	public List<Stock> findStockByClientAndLivreur(@Param("ice")String iceClient,@Param("x")String cniLivreur);
	
	@Query("SELECT s FROM Stock s WHERE s.client.ice=:ice and s.agence.id=:id")
	public List<Stock> findStockByClientAndAgence(@Param("ice")String iceClient,@Param("id")long agenceId);
	
	
	//@Query("SELECT s FROM Stock s WHERE s.produit.id=:id")
	//public List<Stock> findStockByProduitId(@Param("id")long produitId);
	
	@Query("SELECT s FROM Stock s WHERE s.client.ice=:ice and s.qteNonLivre>0 ")
	public List<Stock> findStockDisponibleByClient(@Param("ice")String iceClient);
	
	@Query("SELECT s FROM Stock s WHERE s.livreur.cni=:cni and s.qteNonLivre>0")
	public List<Stock> findStockDisponibleByLivreur(@Param("cni")String cniLivreur);
	
	@Query("SELECT s FROM Stock s WHERE s.agence.id=:idAgence and s.qteNonLivre>0")
	public List<Stock> findStockDisponibleByAgence(@Param("idAgence")long idAgence);
	
	@Query("SELECT s FROM Stock s WHERE s.client.ice=:ice ")
	public List<Stock> findStockByClient(@Param("ice")String iceClient);
	
	@Query("SELECT s FROM Stock s WHERE s.livreur.cni=:cni")
	public List<Stock> findStockByLivreur(@Param("cni")String cniLivreur);
	
	@Query("SELECT s FROM Stock s WHERE s.agence.id=:idAgence")
	public List<Stock> findStockByAgence(@Param("idAgence")long idAgence);
	
	@Query("SELECT s FROM Stock s WHERE s.client.ice=:ice and s.livreur.cni=:x and s.qteNonLivre>0")
	public List<Stock> findStockDisponibleByClientAndLivreur(@Param("ice")String iceClient,@Param("x")String cniLivreur);
	
	@Query("SELECT s FROM Stock s WHERE s.client.ice=:ice and s.agence.id=:x and s.qteNonLivre>0")
	public List<Stock> findStockDisponibleByClientAndAgence(@Param("ice")String iceClient,@Param("x")long agenceId);
	
	
	
	
	
}
