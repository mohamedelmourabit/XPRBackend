package com.xpr.services;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.xpr.entities.Stock;

public interface StockService {

	public Stock  saveStock(Stock entity);

	public List<Stock> findStockByClientAndVarianteSku(String iceClient, String sku);

	public List<Stock> findStockByClientAndVarianteSkuAndVille(String iceClient, String sku, String ville) ;

	public Page<Stock> findAll(Pageable pageable) ;

	public List<Stock> findStockVarianteSku(String sku);

	public List<Stock> findStockByAgenceAndVarianteSku(Long idAgence, String sku);

	public List<Stock> findStockByLivreurAndVarianteSku(String cniLivreur, String sku) ;

	public Optional<Stock> findById(Long id);

	public List<Stock> findStockByClientAndLivreurAndVille(String iceClient, String ville);

	public List<Stock> findStockByClientAndProduitNature(String iceClient, String nature);

	public List<Stock> findStockByClientAndLivreur(String iceClient, String livreur);

	public List<Stock> findStockByClientAndAgence(String iceClient, long agenceId);

	//public List<Stock> findStockByProduitId(long produitId) ;

	public List<Stock> findStockDisponibleByClient(String iceClient);

	public List<Stock> findStockDisponibleByLivreur(String cniLivreur);

	public List<Stock> findStockDisponibleByAgence(long idAgence);

	public List<Stock> findStockByClient(String iceClient);

	public List<Stock> findStockByLivreur(String cniLivreur);

	public List<Stock> findStockByAgence(long idAgence);

	public List<Stock> findStockDisponibleByClientAndLivreur(String iceClient, String livreur);

	public List<Stock> findStockDisponibleByClientAndAgence(String iceClient, long agenceId);

}
