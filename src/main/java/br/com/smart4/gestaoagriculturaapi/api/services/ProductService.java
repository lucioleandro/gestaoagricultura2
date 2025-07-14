package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Product;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ProductRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.ProductResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.ProductFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.ProductMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Transactional
	public ProductResponse create(ProductRequest request) {
		Product entity = productRepository.save(ProductFactory.fromRequest(request));
		return ProductMapper.toResponse(entity);
	}

	@Transactional
	public ProductResponse update(Long id, ProductRequest request) {
		Product existing = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

		existing.setDescricao(request.getDescricao());
		existing.setUnidadeMedida(request.getUnidadeMedida());
		existing.setSiglaUnidadeMedida(request.getSiglaUnidadeMedida());

		Product updated = productRepository.save(existing);
		return ProductMapper.toResponse(updated);
	}

	public ProductResponse findById(Long id) {
		return productRepository.findById(id)
				.map(ProductMapper::toResponse)
				.orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
	}

	public Page<ProductResponse> findAll(Pageable pageable) {
		return productRepository
				.findAll(pageable)
				.map(ProductMapper::toResponse);
	}

	@Transactional
	public void remove(Long id) {
		Product existing = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
		productRepository.delete(existing);
	}
}
