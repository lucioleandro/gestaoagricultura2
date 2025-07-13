package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Product;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ProductRequest;
import br.com.smart4.gestaoagriculturaapi.api.factories.ProductFactory;
import br.com.smart4.gestaoagriculturaapi.api.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Transactional
	public Product create(ProductRequest product) {
		return productRepository.save(ProductFactory.fromRequest(product));
	}

	@Transactional
	public Product update(ProductRequest product) {
		return productRepository.save(ProductFactory.fromRequest(product));
	}

	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Transactional
	public void remove(Product product) {
		productRepository.delete(product);
	}
	
}
