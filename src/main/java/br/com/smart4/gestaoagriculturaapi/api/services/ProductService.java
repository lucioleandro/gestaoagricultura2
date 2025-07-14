package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Product;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ProductRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.ProductResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.ProductFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.ProductMapper;
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
	public ProductResponse create(ProductRequest product) {
		Product entity = productRepository.save(
				ProductFactory.fromRequest(product)
		);
		return ProductMapper.toResponse(entity);
	}

	@Transactional
	public ProductResponse update(ProductRequest product) {
		Product entity = productRepository.save(
				ProductFactory.fromRequest(product)
		);
		return ProductMapper.toResponse(entity);
	}

	public Optional<ProductResponse> findById(Long id) {
		return productRepository.findById(id)
				.map(ProductMapper::toResponse);
	}

	public List<ProductResponse> findAll() {
		return ProductMapper.toListResponse(
				productRepository.findAll()
		);
	}

	@Transactional
	public void remove(Product product) {
		productRepository.delete(product);
	}
}
