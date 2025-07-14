package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Product;
import br.com.smart4.gestaoagriculturaapi.api.domains.ProductImage;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.ProductImageRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.ProductImageResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.ProductImageFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.ProductImageMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.ProductImageRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductImageService {

	private final ProductImageRepository productImageRepository;
	private final ProductRepository productRepository;

	public ProductImageService(ProductImageRepository productImageRepository, ProductRepository productRepository) {
		this.productImageRepository = productImageRepository;
        this.productRepository = productRepository;
    }

	@Transactional
	public ProductImageResponse create(ProductImageRequest request) {
		ProductImage entity = productImageRepository.save(
				ProductImageFactory.fromRequest(request)
		);
		return ProductImageMapper.toResponse(entity);
	}

	@Transactional
	public ProductImageResponse update(Long id, ProductImageRequest request) {
		ProductImage existing = productImageRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("ProductImage not found with id: " + id));

		Product product = productRepository.findById(request.getProductId())
				.orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + request.getProductId()));

		existing.setArquivo(request.getArquivo());
		existing.setExtensao(request.getExtensao());
		existing.setProduct(product);

		ProductImage updated = productImageRepository.save(existing);
		return ProductImageMapper.toResponse(updated);
	}

	public ProductImageResponse findById(Long id) {
		return productImageRepository.findById(id)
				.map(ProductImageMapper::toResponse)
				.orElseThrow(() -> new EntityNotFoundException("ProductImage not found with id: " + id));
	}

	public List<ProductImageResponse> findAll() {
		return ProductImageMapper.toListResponse(
				productImageRepository.findAll()
		);
	}

	public List<ProductImageResponse> findByProductId(Long productId) {
		return ProductImageMapper.toListResponse(
				productImageRepository.findByProductId(productId)
		);
	}

	@Transactional
	public void remove(Long id) {
		ProductImage existing = productImageRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("ProductImage not found with id: " + id));
		productImageRepository.delete(existing);
	}
}
