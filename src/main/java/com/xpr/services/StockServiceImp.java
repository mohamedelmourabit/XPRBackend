package com.xpr.services;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.xpr.dao.StockRepository;
import com.xpr.entities.Stock;


@Service
public class StockServiceImp implements StockService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StockServiceImp.class);
	
	@Autowired
	private StockRepository stockRepository;


	@Override
	public Stock saveStock(Stock entity) {
		return stockRepository.save(entity);
	}

	@Override
	public List<Stock> findStockByClientAndVarianteSku(String iceClient, String sku) {
		return stockRepository.findStockByClientAndVarianteSku(iceClient, sku);
	}

	@Override
	public List<Stock> findStockByClientAndVarianteSkuAndVille(String iceClient, String sku, String ville) {
		return stockRepository.findStockByClientAndVarianteSkuAndVille(iceClient, sku, ville);
	}

	@Override
	public Page<Stock> findAll(Pageable pageable) {
		return stockRepository.findAll(pageable);
	}

	@Override
	public List<Stock> findStockVarianteSku(String sku) {
		return stockRepository.findStockVarianteSku(sku);
	}
	@Override
	public List<Stock> findStockByAgenceAndVarianteSku(Long idAgence, String sku) {
		return stockRepository.findStockByAgenceAndVarianteSku(idAgence, sku);
	}

	@Override
	public List<Stock> findStockByLivreurAndVarianteSku(String cniLivreur, String sku) {
		return stockRepository.findStockByLivreurAndVarianteSku(cniLivreur, sku);
	}
	@Override
	public Optional<Stock> findById(Long id) {
		return stockRepository.findById(id);
	}

	@Override
	public List<Stock> findStockByClientAndLivreurAndVille(String iceClient, String ville) {
		return stockRepository.findStockByClientAndLivreurAndVille(iceClient, ville);
	}

	@Override
	public List<Stock> findStockByClientAndProduitNature(String iceClient, String nature) {
		return stockRepository.findStockByClientAndProduitNature(iceClient, nature);
	}

	@Override
	public List<Stock> findStockByClientAndLivreur(String iceClient, String livreur) {
		return stockRepository.findStockByClientAndLivreur(iceClient, livreur);
	}
	@Override
	public List<Stock> findStockByClientAndAgence(String iceClient, long agenceId) {
		return stockRepository.findStockByClientAndAgence(iceClient, agenceId);
	}
	
	@Override
	public List<Stock> findStockDisponibleByClient(String iceClient) {
		return stockRepository.findStockDisponibleByClient(iceClient);
	}
	@Override
	public List<Stock> findStockDisponibleByLivreur(String cniLivreur) {
		return stockRepository.findStockDisponibleByLivreur(cniLivreur);
	}
	@Override
	public List<Stock> findStockDisponibleByAgence(long idAgence) {
		return stockRepository.findStockDisponibleByAgence(idAgence);
	}
	@Override
	public List<Stock> findStockByClient(String iceClient) {
		return stockRepository.findStockByClient(iceClient);
	}
	@Override
	public List<Stock> findStockByLivreur(String cniLivreur) {
		return stockRepository.findStockByLivreur(cniLivreur);
	}
	@Override
	public List<Stock> findStockByAgence(long idAgence) {
		return stockRepository.findStockByAgence(idAgence);
	}
	@Override
	public List<Stock> findStockDisponibleByClientAndLivreur(String iceClient, String livreur) {
		return stockRepository.findStockDisponibleByClientAndLivreur(iceClient, livreur);
	}
	@Override
	public List<Stock> findStockDisponibleByClientAndAgence(String iceClient, long agenceId) {
		return stockRepository.findStockDisponibleByClientAndAgence(iceClient, agenceId);
	}

	
	

	
	


}
